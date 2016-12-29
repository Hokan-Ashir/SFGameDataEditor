package sfgamedataeditor.views.main.modules.units.races.units.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.creature.parameters.GUIElements;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class UnitsParametersView implements ControllableView {

    private static final int CREATURE_PARAMETERS_TAB_INDEX = 0;
    private static final int CREATURE_EQUIPMENT_TAB_INDEX = 1;
    public static final int CREATURE_SPELLS_TAB_INDEX = 2;

    private JPanel mainPanel;

    private JTabbedPane tabPane;
    private JPanel commonParametersPanel;
    private JPanel equipmentPanel;

    @GUIElement(GUIElementId = GUIElements.HEAD_SLOT, DTOColumnNames = "itemId", DTOClass = CreatureEquipmentObject.class)
    private JPanel headSlotPanel;

    @GUIElement(GUIElementId = GUIElements.LEFT_HAND_SLOT, DTOColumnNames = "itemId", DTOClass = CreatureEquipmentObject.class)
    private JPanel leftHandSlotPanel;

    @GUIElement(GUIElementId = GUIElements.RIGHT_HAND_SLOT, DTOColumnNames = "itemId", DTOClass = CreatureEquipmentObject.class)
    private JPanel rightHandSlotPanel;

    @GUIElement(GUIElementId = GUIElements.CHEST_SLOT, DTOColumnNames = "itemId", DTOClass = CreatureEquipmentObject.class)
    private JPanel chestSlotPanel;

    @GUIElement(GUIElementId = GUIElements.LEGS_SLOT, DTOColumnNames = "itemId", DTOClass = CreatureEquipmentObject.class)
    private JPanel legsSlotPanel;

    @GUIElement(GUIElementId = GUIElements.LEFT_RING_SLOT, DTOColumnNames = "itemId", DTOClass = CreatureEquipmentObject.class)
    private JPanel leftRingSlotPanel;

    @GUIElement(GUIElementId = GUIElements.RIGHT_RING_SLOT, DTOColumnNames = "itemId", DTOClass = CreatureEquipmentObject.class)
    private JPanel rightRingSlotPanel;
    private JLabel headSlotLabel;
    private JLabel leftHandWeaponSlotLabel;
    private JLabel chestSlotLabel;
    private JLabel rightHandWeaponSlotLabel;
    private JLabel leftRingSlotLabel;
    private JLabel rightRingSlotLabel;
    private JLabel legsSlotLabel;
    private JPanel spellsPanel;

    @GUIElement(GUIElementId = GUIElements.EXPERIENCE, DTOColumnNames = "experience", DTOClass = CreaturesCommonParameterObject.class)
    private JPanel experiencePanel;

    @GUIElement(GUIElementId = GUIElements.ARMOR, DTOColumnNames = "armor", DTOClass = CreaturesCommonParameterObject.class)
    private JPanel armorPanel;

    // TODO assuming creature have 3 spells at most, though ordinary it has 2 top
    @GUIElement(GUIElementId = GUIElements.SPELL1, DTOColumnNames = "spellNumber", DTOClass = CreatureSpellObject.class)
    private JPanel spell1Panel;

    @GUIElement(GUIElementId = GUIElements.SPELL2, DTOColumnNames = "spellNumber", DTOClass = CreatureSpellObject.class)
    private JPanel spell2Panel;

    @GUIElement(GUIElementId = GUIElements.SPELL3, DTOColumnNames = "spellNumber", DTOClass = CreatureSpellObject.class)
    private JPanel spell3Panel;

    public UnitsParametersView() {
        internationalizeEquipmentLabels();
        internationalizeTabs();
    }

    private void internationalizeTabs() {
        tabPane.setTitleAt(CREATURE_PARAMETERS_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "tab.creature.parameters"));
        tabPane.setTitleAt(CREATURE_EQUIPMENT_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "tab.creature.equipment"));
        tabPane.setTitleAt(CREATURE_SPELLS_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "tab.creature.spells"));
    }

    private void internationalizeEquipmentLabels() {
        headSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.head.slot.label"));
        leftHandWeaponSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.left.hand.slot.label"));
        rightHandWeaponSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.right.hand.slot.label"));
        chestSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.chest.slot.label"));
        leftRingSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.left.ring.slot.label"));
        rightRingSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.right.ring.slot.label"));
        legsSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.legs.slot.label"));
    }

    public JTabbedPane getTabPane() {
        return tabPane;
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return UnitsParametersController.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
