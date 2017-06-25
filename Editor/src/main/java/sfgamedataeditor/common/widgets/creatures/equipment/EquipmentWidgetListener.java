package sfgamedataeditor.common.widgets.creatures.equipment;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.common.widgets.items.weapons.type.WeaponTypesMap;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.SubViewPanelTuple;
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

        // TODO FIX
        String itemName = I18NService.INSTANCE.getMessage(I18NTypes.PLAYER_LEVEL_STATS_GUI, String.valueOf(itemId));
        String itemTypeName;
        boolean isItemOrb = ArmorParametersTableService.INSTANCE.getOrbNames().contains(itemName);
        if (isItemOrb) {
            itemTypeName = WeaponTypesMap.INSTANCE.getWeaponTypeNameById(ORB_TYPE_WEAPON);
        } else {
            WeaponParametersObject weaponParametersObject = WeaponParametersTableService.INSTANCE.getObjectByItemId(itemId);
            if (weaponParametersObject != null) {
                itemTypeName = WeaponTypesMap.INSTANCE.getWeaponTypeNameById(weaponParametersObject.type);
            } else {
                int itemPieceId = ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId);
                String itemPieceNameKey = ViewTools.getKeyStringByPropertyValue(String.valueOf(itemPieceId), I18NTypes.ITEM_TYPES_NAME_MAPPING);
                itemTypeName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, itemPieceNameKey);
            }
        }

        getWidget().getItemTypeComboBox().setSelectedItem(itemTypeName);
        updateItemNames();
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
        SubViewPanelTuple tuple = (SubViewPanelTuple) getWidget().getItemPieceComboBox().getSelectedItem();
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
        String itemTypeName = (String) getWidget().getItemTypeComboBox().getSelectedItem();
        String itemTypeKey = ViewTools.getKeyStringByPropertyValue(itemTypeName, I18NTypes.COMMON);
        Integer itemType;
        if (itemTypeKey == null) {
            itemType = WeaponTypesMap.INSTANCE.getWeaponTypeByName(itemTypeName);
        } else {
            itemType = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeKey));
        }
        return itemType;
    }

    private void updateItemNames() {
        String itemTypeName = (String) getWidget().getItemTypeComboBox().getSelectedItem();
        String itemTypeI18NKey = ViewTools.getKeyStringByPropertyValue(itemTypeName, I18NTypes.COMMON);
        List<SubViewPanelTuple> itemNames;
        if (itemTypeI18NKey == null) {
            Integer weaponType = WeaponTypesMap.INSTANCE.getWeaponTypeByName(itemTypeName);
            itemNames = WeaponParametersTableService.INSTANCE.getItemsByItemType(weaponType);
            if (itemNames.isEmpty()) {
                itemNames = ArmorParametersTableService.INSTANCE.getOrbNames();
            }
        } else {
            String itemPieceType = I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeI18NKey);
            itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(Integer.parseInt(itemPieceType));
        }

        final JComboBox<SubViewPanelTuple> itemPieceComboBox = getWidget().getItemPieceComboBox();
        itemPieceComboBox.removeAllItems();
        for (SubViewPanelTuple itemName : itemNames) {
            itemPieceComboBox.addItem(itemName);
        }
    }
}
