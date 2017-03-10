package sfgamedataeditor.common.widgets.creatures.equipment;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EquipmentWidget extends AbstractWidget<EquipmentWidgetListener> {
    private JPanel mainPanel;
    private JLabel itemTypeLabel;
    private JComboBox<String> itemTypeComboBox;
    private JLabel itemPieceLabel;
    private JComboBox<String> itemPieceComboBox;
    private JButton goToItemButton;

    public EquipmentWidget() {
        itemTypeLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.slot.item.type"));
        itemPieceLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.slot.item.name"));

        initializeItemTypesComboBox();
        add(getMainPanel());
    }

    // TODO possibly restrict users, so that they can't mindlessly put i.e. rings in chest slot
    private void initializeItemTypesComboBox() {
        List<String> i18nItemTypeKeys = new ArrayList<String>() {{
            add("items.armor.helmets");
            add("items.armor.chest.armor");
            add("items.armor.robes");
            add("items.armor.legs.armor");
            add("items.armor.shield");
            add("items.armor.rings");
            add("items.figureNPC");
            add("items.hero.army.units");
            add("items.scrolls");
            add("items.miscellaneous");
            add("items.rune.workers.in.inventory.humans");
            add("items.rune.workers.in.inventory.elves");
            add("items.rune.workers.in.inventory.dwarves");
            add("items.rune.workers.in.inventory.orcs");
            add("items.rune.workers.in.inventory.trolls");
            add("items.rune.workers.in.inventory.dark.elves");

            add("items.rune.hero.in.inventory");

            add("items.unit.plan.in.inventory.humans");
            add("items.unit.plan.in.inventory.elves");
            add("items.unit.plan.in.inventory.dwarves");
            add("items.unit.plan.in.inventory.orcs");
            add("items.unit.plan.in.inventory.trolls");
            add("items.unit.plan.in.inventory.dark.elves");

            add("items.building.plan.in.inventory.humans");
            add("items.building.plan.in.inventory.elves");
            add("items.building.plan.in.inventory.dwarves");
            add("items.building.plan.in.inventory.orcs");
            add("items.building.plan.in.inventory.trolls");
            add("items.building.plan.in.inventory.dark.elves");

            add("items.blank.scrolls");
        }};

        List<String> itemNames = new ArrayList<>();
        for (String i18nItemTypeKey : i18nItemTypeKeys) {
            itemNames.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, i18nItemTypeKey));
        }

        List<String> weaponItemTypeKeys = new ArrayList<String>() {{
            add("type.default");
            add("type.hand");
            add("type.1h.dagger");
            add("type.1h.sword");
            add("type.1h.axe");
            add("type.1h.mace.spiky");
            add("type.1h.hammer");
            add("type.1h.staff");
            add("type.2h.sword");
            add("type.2h.axe");
            add("type.2h.mace");
            add("type.2h.hammer");
            add("type.2h.staff");
            add("type.2h.spear");
            add("type.2h.halberd");
            add("type.2h.bow");
            add("type.2h.crossbow");
            add("type.1h.mace.blunt");
            add("type.claw");
            add("type.mouth");
            add("type.stone.thrower");
        }};

        for (String i18nItemTypeKey : weaponItemTypeKeys) {
            itemNames.add(I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, i18nItemTypeKey));
        }

        Collections.sort(itemNames);

        for (String itemName : itemNames) {
            itemTypeComboBox.addItem(itemName);
        }
    }

    public JButton getGoToItemButton() {
        return goToItemButton;
    }

    public JComboBox<String> getItemTypeComboBox() {
        return itemTypeComboBox;
    }

    public JComboBox<String> getItemPieceComboBox() {
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
