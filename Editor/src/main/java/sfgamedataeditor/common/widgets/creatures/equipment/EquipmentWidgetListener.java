package sfgamedataeditor.common.widgets.creatures.equipment;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.common.widgets.items.weapons.type.WeaponTypesMap;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.Set;

public class EquipmentWidgetListener extends AbstractWidgetListener<EquipmentWidget, OffsetableObject> implements ItemListener, ActionListener {

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

        String itemName = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(itemId));
        String itemTypeName;
        WeaponParametersObject weaponParametersObject = WeaponParametersTableService.INSTANCE.getObjectByItemId(itemId);
        if (weaponParametersObject != null) {
            itemTypeName = WeaponTypesMap.INSTANCE.getWeaponTypeNameById(weaponParametersObject.type);
        } else {
            int itemPieceId = ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId);
            String itemPieceNameKey = ViewTools.getKeyStringByPropertyValue(String.valueOf(itemPieceId), I18NTypes.ITEM_TYPES_NAME_MAPPING);
            itemTypeName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, itemPieceNameKey);
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
        String selectedItemName = (String) getWidget().getItemPieceComboBox().getSelectedItem();
        String itemTypeName = (String) getWidget().getItemTypeComboBox().getSelectedItem();
        String itemTypeKey = ViewTools.getKeyStringByPropertyValue(itemTypeName, I18NTypes.COMMON);
        Integer itemId;
        if (itemTypeKey == null) {
            Integer weaponType = WeaponTypesMap.INSTANCE.getWeaponTypeByName(itemTypeName);
            itemId = WeaponParametersTableService.INSTANCE.getItemIdByItemTypeAndName(selectedItemName, weaponType);
        } else {
            Integer itemType = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeKey));
            itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(selectedItemName, itemType);
        }
        return itemId;
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
        final Set<String> itemNames;
        if (itemTypeI18NKey == null) {
            Integer weaponType = WeaponTypesMap.INSTANCE.getWeaponTypeByName(itemTypeName);
            itemNames = WeaponParametersTableService.INSTANCE.getItemsByItemType(weaponType);
        } else {
            String itemPieceType = I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeI18NKey);
            itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(Integer.parseInt(itemPieceType));
        }

        final JComboBox<String> itemPieceComboBox = getWidget().getItemPieceComboBox();
        itemPieceComboBox.removeAllItems();
        for (String itemName : itemNames) {
            itemPieceComboBox.addItem(itemName);
        }
    }
}
