package sfgamedataeditor.common.widgets.creatures.equipment;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
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
        
        List<ObjectTuple> objectTuples = new ArrayList<>();
        createCommonTypes(objectTuples);
        createWeponTypes(objectTuples);
        Collections.sort(objectTuples);

        for (ObjectTuple objectTuple : objectTuples) {
            itemTypeComboBox.addItem(objectTuple);
        }
    }

    private void createWeponTypes(List<ObjectTuple> objectTuples) {
        objectTuples.add(createWeaponType("type.default", 1));
        objectTuples.add(createWeaponType("type.hand", 2));
        objectTuples.add(createWeaponType("type.1h.dagger", 3));
        objectTuples.add(createWeaponType("type.1h.sword", 4));
        objectTuples.add(createWeaponType("type.1h.axe", 5));
        objectTuples.add(createWeaponType("type.1h.mace.spiky", 6));
        objectTuples.add(createWeaponType("type.1h.hammer", 7));
        objectTuples.add(createWeaponType("type.1h.staff", 8));
        objectTuples.add(createWeaponType("type.2h.sword", 9));
        objectTuples.add(createWeaponType("type.2h.axe", 10));
        objectTuples.add(createWeaponType("type.2h.mace", 11));
        objectTuples.add(createWeaponType("type.2h.hammer", 12));
        objectTuples.add(createWeaponType("type.2h.staff", 13));
        objectTuples.add(createWeaponType("type.2h.spear", 14));
        objectTuples.add(createWeaponType("type.2h.halberd", 15));
        objectTuples.add(createWeaponType("type.2h.bow", 16));
        objectTuples.add(createWeaponType("type.2h.crossbow", 17));
        objectTuples.add(createWeaponType("type.1h.mace.blunt", 18));
        objectTuples.add(createWeaponType("type.claw", 19));
        objectTuples.add(createWeaponType("type.mouth", 20));
        objectTuples.add(createWeaponType("type.stone.thrower", 21));
        objectTuples.add(createWeaponType("type.orb", 22));
    }

    private void createCommonTypes(List<ObjectTuple> objectTuples) {
        objectTuples.add(createCommonType("items.no.type"));

        objectTuples.add(createCommonType("items.armor.helmets"));
        objectTuples.add(createCommonType("items.armor.chest.armor"));
        objectTuples.add(createCommonType("items.armor.robes"));
        objectTuples.add(createCommonType("items.armor.legs.armor"));
        objectTuples.add(createCommonType("items.armor.shield"));
        objectTuples.add(createCommonType("items.armor.rings"));
        objectTuples.add(createCommonType("items.figureNPC"));
        objectTuples.add(createCommonType("items.hero.army.units"));
        objectTuples.add(createCommonType("items.scrolls"));
        objectTuples.add(createCommonType("items.miscellaneous"));
        objectTuples.add(createCommonType("items.rune.workers.in.inventory.humans"));
        objectTuples.add(createCommonType("items.rune.workers.in.inventory.elves"));
        objectTuples.add(createCommonType("items.rune.workers.in.inventory.dwarves"));
        objectTuples.add(createCommonType("items.rune.workers.in.inventory.orcs"));
        objectTuples.add(createCommonType("items.rune.workers.in.inventory.trolls"));
        objectTuples.add(createCommonType("items.rune.workers.in.inventory.dark.elves"));

        objectTuples.add(createCommonType("items.rune.hero.in.inventory"));

        objectTuples.add(createCommonType("items.unit.plan.in.inventory.humans"));
        objectTuples.add(createCommonType("items.unit.plan.in.inventory.elves"));
        objectTuples.add(createCommonType("items.unit.plan.in.inventory.dwarves"));
        objectTuples.add(createCommonType("items.unit.plan.in.inventory.orcs"));
        objectTuples.add(createCommonType("items.unit.plan.in.inventory.trolls"));
        objectTuples.add(createCommonType("items.unit.plan.in.inventory.dark.elves"));

        objectTuples.add(createCommonType("items.building.plan.in.inventory.humans"));
        objectTuples.add(createCommonType("items.building.plan.in.inventory.elves"));
        objectTuples.add(createCommonType("items.building.plan.in.inventory.dwarves"));
        objectTuples.add(createCommonType("items.building.plan.in.inventory.orcs"));
        objectTuples.add(createCommonType("items.building.plan.in.inventory.trolls"));
        objectTuples.add(createCommonType("items.building.plan.in.inventory.dark.elves"));

        objectTuples.add(createCommonType("items.blank.scrolls"));
    }

    private ObjectTuple createCommonType(String i18nKey) {
        return new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, i18nKey),
                Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, i18nKey)));
    }

    private ObjectTuple createWeaponType(String i18nKey, int typeId) {
        return new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, i18nKey), typeId);
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
