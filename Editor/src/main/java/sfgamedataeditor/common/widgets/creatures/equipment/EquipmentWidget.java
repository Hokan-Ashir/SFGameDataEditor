package sfgamedataeditor.common.widgets.creatures.equipment;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

@SuppressWarnings("unused")
public class EquipmentWidget extends AbstractWidget<EquipmentWidgetListener> {
    private JPanel mainPanel;
    private JLabel itemTypeLabel;
    private JComboBox<ObjectTuple> itemTypeComboBox;
    private JLabel itemPieceLabel;
    private JComboBox<ObjectTuple> itemPieceComboBox;
    private JButton goToItemButton;

    public EquipmentWidget() {
        itemTypeLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.slot.item.type"));
        itemPieceLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.slot.item.name"));

        initializeItemTypesComboBox();
        add(getMainPanel());
    }

    // TODO possibly restrict users, so that they can't mindlessly put i.e. rings in chest slot
    private void initializeItemTypesComboBox() {
        addCommonType("items.no.type");

        addCommonType("items.armor.helmets");
        addCommonType("items.armor.chest.armor");
        addCommonType("items.armor.robes");
        addCommonType("items.armor.legs.armor");
        addCommonType("items.armor.shield");
        addCommonType("items.armor.rings");
        addCommonType("items.figureNPC");
        addCommonType("items.hero.army.units");
        addCommonType("items.scrolls");
        addCommonType("items.miscellaneous");
        addCommonType("items.rune.workers.in.inventory.humans");
        addCommonType("items.rune.workers.in.inventory.elves");
        addCommonType("items.rune.workers.in.inventory.dwarves");
        addCommonType("items.rune.workers.in.inventory.orcs");
        addCommonType("items.rune.workers.in.inventory.trolls");
        addCommonType("items.rune.workers.in.inventory.dark.elves");

        addCommonType("items.rune.hero.in.inventory");

        addCommonType("items.unit.plan.in.inventory.humans");
        addCommonType("items.unit.plan.in.inventory.elves");
        addCommonType("items.unit.plan.in.inventory.dwarves");
        addCommonType("items.unit.plan.in.inventory.orcs");
        addCommonType("items.unit.plan.in.inventory.trolls");
        addCommonType("items.unit.plan.in.inventory.dark.elves");

        addCommonType("items.building.plan.in.inventory.humans");
        addCommonType("items.building.plan.in.inventory.elves");
        addCommonType("items.building.plan.in.inventory.dwarves");
        addCommonType("items.building.plan.in.inventory.orcs");
        addCommonType("items.building.plan.in.inventory.trolls");
        addCommonType("items.building.plan.in.inventory.dark.elves");

        addCommonType("items.blank.scrolls");

        addWeaponType("type.default", 1);
        addWeaponType("type.hand", 2);
        addWeaponType("type.1h.dagger", 3);
        addWeaponType("type.1h.sword", 4);
        addWeaponType("type.1h.axe", 5);
        addWeaponType("type.1h.mace.spiky", 6);
        addWeaponType("type.1h.hammer", 7);
        addWeaponType("type.1h.staff", 8);
        addWeaponType("type.2h.sword", 9);
        addWeaponType("type.2h.axe", 10);
        addWeaponType("type.2h.mace", 11);
        addWeaponType("type.2h.hammer", 12);
        addWeaponType("type.2h.staff", 13);
        addWeaponType("type.2h.spear", 14);
        addWeaponType("type.2h.halberd", 15);
        addWeaponType("type.2h.bow", 16);
        addWeaponType("type.2h.crossbow", 17);
        addWeaponType("type.1h.mace.blunt", 18);
        addWeaponType("type.claw", 19);
        addWeaponType("type.mouth", 20);
        addWeaponType("type.stone.thrower", 21);
        addWeaponType("type.orb", 22);
    }

    private void addCommonType(String i18nKey) {
        itemTypeComboBox.addItem(new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, i18nKey),
                Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, i18nKey))));
    }

    private void addWeaponType(String i18nKey, int typeId) {
        itemTypeComboBox.addItem(new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, i18nKey), typeId));
    }

    JButton getGoToItemButton() {
        return goToItemButton;
    }

    JComboBox<ObjectTuple> getItemTypeComboBox() {
        return itemTypeComboBox;
    }

    JComboBox<ObjectTuple> getItemPieceComboBox() {
        return itemPieceComboBox;
    }

    @Override
    protected void insertListener(EquipmentWidgetListener listener) {
        itemTypeComboBox.addItemListener(listener);
        itemPieceComboBox.addItemListener(listener);
        goToItemButton.addActionListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {

    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        itemTypeLabel.setVisible(aFlag);
        itemTypeComboBox.setVisible(aFlag);
        itemPieceLabel.setVisible(aFlag);
        itemPieceComboBox.setVisible(aFlag);
    }
}
