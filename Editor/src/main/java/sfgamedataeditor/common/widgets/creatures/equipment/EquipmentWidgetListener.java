package sfgamedataeditor.common.widgets.creatures.equipment;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.common.widgets.items.weapons.type.WeaponTypesMap;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;

public class EquipmentWidgetListener extends AbstractWidgetListener<EquipmentWidget, OffsetableObject> implements ItemListener, ActionListener {

    private static final int ORB_TYPE_WEAPON = 22;

    public EquipmentWidgetListener(EquipmentWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        int itemId = getSelectedItemId();
        return new int[]{itemId};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int itemId = value[0];
        if (itemId == 0) {
            return;
        }

        ItemPriceParametersObject object = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        String itemTypeName;
        List<ObjectTuple> tuples = ArmorParametersTableService.INSTANCE.getOrbNames();
        boolean isItemOrb = false;
        for (ObjectTuple tuple : tuples) {
            if (tuple.getObjectId().equals(itemId)) {
                isItemOrb = true;
            }
        }

        Integer itemType;
        if (isItemOrb) {
            itemType = ORB_TYPE_WEAPON;
            itemTypeName = WeaponTypesMap.INSTANCE.getWeaponTypeNameById(ORB_TYPE_WEAPON);
        } else {
            WeaponParametersObject weaponParametersObject = WeaponParametersTableService.INSTANCE.getObjectByItemId(itemId);
            if (weaponParametersObject != null) {
                itemType = weaponParametersObject.type;
                itemTypeName = WeaponTypesMap.INSTANCE.getWeaponTypeNameById(weaponParametersObject.type);
            } else {
                itemType = object.typeId;
                String itemPieceNameKey = ViewTools.getKeyStringByPropertyValue(String.valueOf(itemType), I18NTypes.ITEM_TYPES_NAME_MAPPING);
                itemTypeName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, itemPieceNameKey);
            }
        }

        getWidget().getItemTypeComboBox().setSelectedItem(new ObjectTuple(itemTypeName, itemType));
        updateItemNames();
        ObjectTuple itemName = TextTableService.INSTANCE.getObjectTuple(object.nameId, object.itemId);
        getWidget().getItemPieceComboBox().setSelectedItem(itemName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getWidget().getItemPieceComboBox().getSelectedItem() == null) {
            return;
        }

        Integer itemId = getSelectedItemId();
        Class<? extends PresentableView> classViewToShow = EquipmentMapping.INSTANCE.getItemParametersViewClassByItemId(itemId);
        Model model = EquipmentMapping.INSTANCE.createModel(itemId);
        EventProcessor.INSTANCE.process(new ShowContentViewEvent(classViewToShow, model));
    }

    private Integer getSelectedItemId() {
        ObjectTuple tuple = (ObjectTuple) getWidget().getItemPieceComboBox().getSelectedItem();
        return tuple.getObjectId();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        if (e.getSource().equals(getWidget().getItemTypeComboBox())) {
            updateItemNames();
            int selectedItemType = getSelectedItemType();
            boolean itemTypeHasNoParameters = EquipmentMapping.INSTANCE.isItemTypeHasNoParameters(selectedItemType);
            getWidget().getGoToItemButton().setEnabled(!itemTypeHasNoParameters);
            return;
        }

        if (e.getSource().equals(getWidget().getItemPieceComboBox())) {
            setWidgetValueToDTOField();
        }
    }

    private int getSelectedItemType() {
        ObjectTuple tuple = (ObjectTuple) getWidget().getItemTypeComboBox().getSelectedItem();
        return tuple.getObjectId();
    }

    private void updateItemNames() {
        // TODO incorrect select - "Hand - 2" is clashed with "items.rune.hero.in.inventory - 2"
        ObjectTuple tuple = (ObjectTuple) getWidget().getItemTypeComboBox().getSelectedItem();
        String itemTypeI18NKey = ViewTools.getKeyStringByPropertyValue(String.valueOf(tuple.getObjectId()), I18NTypes.ITEM_TYPES_NAME_MAPPING);
        List<ObjectTuple> itemNames;
        if (itemTypeI18NKey == null) {
            itemNames = WeaponParametersTableService.INSTANCE.getItemsByItemType(tuple.getObjectId());
            if (itemNames.isEmpty()) {
                itemNames = ArmorParametersTableService.INSTANCE.getOrbNames();
            }
        } else {
            itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(tuple.getObjectId());
        }

        final JComboBox<ObjectTuple> itemPieceComboBox = getWidget().getItemPieceComboBox();
        itemPieceComboBox.removeAllItems();
        for (ObjectTuple itemName : itemNames) {
            itemPieceComboBox.addItem(itemName);
        }
    }
}
