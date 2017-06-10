package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.creature.parameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.corpseloot.CreatureCorpseLootObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.WidgetsComboBoxListener;
import sfgamedataeditor.views.common.presenters.AbstractParametersPresenter;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreaturesParametersPresenter extends AbstractParametersPresenter<CreaturesParametersModelParameter, CreaturesParametersView> implements ListSelectionListener {

    private static final Map<Integer, Integer> SLOT_NUMBER_MAPPING = new HashMap<>();
    private static final Map<Integer, Integer> SPELL_NUMBER_MAPPING = new HashMap<>();
    private final WidgetsComboBoxListener<CreatureCorpseLootObject, CreaturesParametersView> dropItemsListener;

    public CreaturesParametersPresenter(CreaturesParametersView view) {
        super(view);
        initializeSlotNumberMapping();
        initializeSpellNumberMapping();
        dropItemsListener = new WidgetsComboBoxListener<>(getView(), CreatureCorpseLootObject.class, getView().getDropItemsComboBox());
        getView().getDropItemsComboBox().addItemListener(dropItemsListener);
    }

    private void initializeSpellNumberMapping() {
        SPELL_NUMBER_MAPPING.put(GUIElements.SPELL1, 0);
        SPELL_NUMBER_MAPPING.put(GUIElements.SPELL2, 1);
        SPELL_NUMBER_MAPPING.put(GUIElements.SPELL3, 2);
    }

    private void initializeSlotNumberMapping() {
        // see CreaturesEquipmentObject
        //    00 - helmet
        //    01 - right hand
        //    02 - chest
        //    03 - left hand
        //    04 - right ring
        //    05 - legs
        //    06 - left ring
        SLOT_NUMBER_MAPPING.put(GUIElements.HEAD_SLOT, 0);
        SLOT_NUMBER_MAPPING.put(GUIElements.RIGHT_HAND_SLOT, 1);
        SLOT_NUMBER_MAPPING.put(GUIElements.CHEST_SLOT, 2);
        SLOT_NUMBER_MAPPING.put(GUIElements.LEFT_HAND_SLOT, 3);
        SLOT_NUMBER_MAPPING.put(GUIElements.RIGHT_RING_SLOT, 4);
        SLOT_NUMBER_MAPPING.put(GUIElements.LEGS_SLOT, 5);
        SLOT_NUMBER_MAPPING.put(GUIElements.LEFT_RING_SLOT, 6);
    }

    @Override
    public void updateView() {
        CreaturesParametersModelParameter parameter = getModel().getParameter();
        updateCorpseLootObjects(parameter);
        updateMerchantTrades(parameter);

        super.updateView();
        ViewTools.setFirstActiveTab(getView().getTabPane());
    }

    private void updateMerchantTrades(CreaturesParametersModelParameter parameter) {
        List<Integer> itemIds = parameter.getMerchantItemIds();
        if (itemIds != null && !itemIds.isEmpty()) {
            getView().getTabPane().setEnabledAt(CreaturesParametersView.MERCHANT_TRADES_TAB_INDEX, true);
            String selectedItem = parameter.getSelectedMerchantItem();
            updateMerchantInventoryList(itemIds, selectedItem);
        } else {
            getView().getTabPane().setEnabledAt(CreaturesParametersView.MERCHANT_TRADES_TAB_INDEX, false);
        }
    }

    private void updateCorpseLootObjects(CreaturesParametersModelParameter parameter) {
        final List<CreatureCorpseLootObject> corpseLootObjects = parameter.getCorpseLootObjects();

        if (corpseLootObjects != null && !corpseLootObjects.isEmpty()) {
            getView().getTabPane().setEnabledAt(CreaturesParametersView.CORPSE_LOOT_TAB_INDEX, true);
            final JComboBox<String> dropItemsComboBox = getView().getDropItemsComboBox();
            ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(dropItemsComboBox) {
                @Override
                protected void setValues() {
                    dropItemsComboBox.removeAllItems();
                    String dropItem = I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "drop.item");
                    for (int i = 0; i < corpseLootObjects.size(); i++) {
                        dropItemsComboBox.addItem(dropItem + " - " + (i + 1));
                    }
                    dropItemsComboBox.setSelectedItem(null);
                }
            });

            dropItemsListener.setWidgetObjects(corpseLootObjects);
            dropItemsComboBox.setSelectedItem(dropItemsComboBox.getItemAt(0));
        } else {
            getView().getTabPane().setEnabledAt(CreaturesParametersView.CORPSE_LOOT_TAB_INDEX, false);
        }
    }

    private void updateMerchantInventoryList(List<Integer> itemIds, String selectedItem) {
        JList<String> inventoryItemList = getView().getMerchantInventoryItemList();
        DefaultListModel<String> model = (DefaultListModel<String>) inventoryItemList.getModel();
        model.removeAllElements();

        inventoryItemList.removeListSelectionListener(this);
        for (Integer itemId : itemIds) {
            String itemName = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(itemId));
            model.addElement(itemName);
        }
        inventoryItemList.addListSelectionListener(this);
        inventoryItemList.setSelectedValue(selectedItem, true);
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {
        CreaturesParametersModelParameter parameter = getModel().getParameter();
        CreatureParameterObject creatureParameterObject = parameter.getCreatureParameterObject();
        CreaturesCommonParameterObject commonParameterObject = parameter.getCreatureCommonParameterObject();
        List<CreatureEquipmentObject> creatureEquipment = parameter.getCreatureEquipment();
        List<CreatureSpellObject> creatureSpells = parameter.getCreatureSpells();

        Class<?> dtoClass = annotation.DTOClass();
        if (dtoClass.equals(CreatureParameterObject.class)) {
            widget.getListener().updateWidgetValue(creatureParameterObject);
        } else if (dtoClass.equals(CreaturesCommonParameterObject.class)) {
            widget.getListener().updateWidgetValue(commonParameterObject);
        } else if (dtoClass.equals(CreatureEquipmentObject.class)) {
            int elementId = annotation.GUIElementId();
            Integer slotNumber = SLOT_NUMBER_MAPPING.get(elementId);
            widget.setVisible(false);
            for (CreatureEquipmentObject creatureEquipmentObject : creatureEquipment) {
                if (creatureEquipmentObject.equipmentSlot.equals(slotNumber)) {
                    widget.setVisible(true);
                    widget.getListener().updateWidgetValue(creatureEquipmentObject);
                    break;
                }
            }
        } else if (dtoClass.equals(CreatureSpellObject.class)) {
            if (creatureSpells == null || creatureSpells.isEmpty()) {
                getView().getTabPane().setEnabledAt(CreaturesParametersView.SPELLS_TAB_INDEX, false);
            } else {
                getView().getTabPane().setEnabledAt(CreaturesParametersView.SPELLS_TAB_INDEX, true);
                Integer spellIndex = SPELL_NUMBER_MAPPING.get(annotation.GUIElementId());
                if (spellIndex >= creatureSpells.size()) {
                    widget.setVisible(false);
                } else {
                    widget.setVisible(true);
                    widget.getListener().updateWidgetValue(creatureSpells.get(spellIndex));
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // do not catch other events except last one
        if (e.getValueIsAdjusting()) {
            return;
        }
        ListModel<String> model = getView().getMerchantInventoryItemList().getModel();
        if (model.getSize() == 0) {
            return;
        }

        getView().getEquipmentWidget().setVisible(true);
        // because list permit only to select one item at a time, it's not important which (first or last) selected
        // index get from click event
        int firstIndex = e.getFirstIndex();
        Integer itemId = getModel().getParameter().getMerchantItemIds().get(firstIndex);
        ItemPriceParametersObject parametersObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        getView().getEquipmentWidget().getListener().updateWidgetValue(parametersObject);
    }
}
