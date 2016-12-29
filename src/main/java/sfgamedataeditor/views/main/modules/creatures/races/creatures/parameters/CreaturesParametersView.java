package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.creature.parameters.GUIElements;
import sfgamedataeditor.common.widgets.Disabled;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class CreaturesParametersView implements ControllableView {

    private static final int CREATURE_PARAMETERS_TAB_INDEX = 0;
    private static final int CREATURE_EQUIPMENT_TAB_INDEX = 1;
    public static final int CREATURE_SPELLS_TAB_INDEX = 2;

    private JPanel mainPanel;

    @Disabled
    @GUIElement(GUIElementId = GUIElements.STATS_ID, DTOColumnNames = "statsId", DTOClass = CreatureParameterObject.class)
    private JPanel statsIdPanel;

    @GUIElement(GUIElementId = GUIElements.LEVEL, DTOColumnNames = "level", DTOClass = CreatureParameterObject.class)
    private JPanel levelPanel;

    @GUIElement(GUIElementId = GUIElements.RACE_ID, DTOColumnNames = "raceId", DTOClass = CreatureParameterObject.class)
    private JPanel raceIdPanel;

    @GUIElement(GUIElementId = GUIElements.STRENGTH, DTOColumnNames = "strength", DTOClass = CreatureParameterObject.class)
    private JPanel strengthPanel;

    @GUIElement(GUIElementId = GUIElements.STAMINA, DTOColumnNames = "stamina", DTOClass = CreatureParameterObject.class)
    private JPanel staminaPanel;

    @GUIElement(GUIElementId = GUIElements.AGILITY, DTOColumnNames = "agility", DTOClass = CreatureParameterObject.class)
    private JPanel agilityPanel;

    @GUIElement(GUIElementId = GUIElements.DEXTERITY, DTOColumnNames = "dexterity", DTOClass = CreatureParameterObject.class)
    private JPanel dexterityPanel;

    @GUIElement(GUIElementId = GUIElements.INTELLIGENCE, DTOColumnNames = "intelligence", DTOClass = CreatureParameterObject.class)
    private JPanel intelligencePanel;

    @GUIElement(GUIElementId = GUIElements.WISDOM, DTOColumnNames = "wisdom", DTOClass = CreatureParameterObject.class)
    private JPanel wisdomPanel;

    @GUIElement(GUIElementId = GUIElements.CHARISMA, DTOColumnNames = "charisma", DTOClass = CreatureParameterObject.class)
    private JPanel charismaPanel;

    @GUIElement(GUIElementId = GUIElements.FIRE_RESISTANCE, DTOColumnNames = "fireResistance", DTOClass = CreatureParameterObject.class)
    private JPanel fireResistancePanel;

    @GUIElement(GUIElementId = GUIElements.ICE_RESISTANCE, DTOColumnNames = "iceResistance", DTOClass = CreatureParameterObject.class)
    private JPanel iceResistancePanel;

    @GUIElement(GUIElementId = GUIElements.MIND_RESISTANCE, DTOColumnNames = "mindResistance", DTOClass = CreatureParameterObject.class)
    private JPanel mindResistancePanel;

    @GUIElement(GUIElementId = GUIElements.BLACK_RESISTANCE, DTOColumnNames = "blackResistance", DTOClass = CreatureParameterObject.class)
    private JPanel blackResistancePanel;

    @GUIElement(GUIElementId = GUIElements.WALK_SPEED, DTOColumnNames = "walkSpeed", DTOClass = CreatureParameterObject.class)
    private JPanel walkSpeedPanel;

    @GUIElement(GUIElementId = GUIElements.FIGHT_SPEED, DTOColumnNames = "fightSpeed", DTOClass = CreatureParameterObject.class)
    private JPanel fightSpeedPanel;

    @GUIElement(GUIElementId = GUIElements.CAST_SPEED, DTOColumnNames = "castSpeed", DTOClass = CreatureParameterObject.class)
    private JPanel castSpeedPanel;

    @GUIElement(GUIElementId = GUIElements.SIZE, DTOColumnNames = "size", DTOClass = CreatureParameterObject.class)
    private JPanel sizePanel;

    @GUIElement(GUIElementId = GUIElements.SPAWN_TIME, DTOColumnNames = "spawnTime", DTOClass = CreatureParameterObject.class)
    private JPanel spawnTimePanel;

    @GUIElement(GUIElementId = GUIElements.GENDER_AND_VULNERABILITY, DTOColumnNames = "genderAndVulnerability", DTOClass = CreatureParameterObject.class)
    private JPanel genderAndVulnerabilityPanel;

    @GUIElement(GUIElementId = GUIElements.HEAD_ID, DTOColumnNames = "headId", DTOClass = CreatureParameterObject.class)
    private JPanel headIdPanel;

    @GUIElement(GUIElementId = GUIElements.EQUIPMENT_SLOTS_ID, DTOColumnNames = "equipmentSlotsId", DTOClass = CreatureParameterObject.class)
    private JPanel equipmentSlotsIdPanel;
    private JPanel characteristicsPanel;
    private JLabel characteristicsLabel;
    private JPanel resistancesPanel;
    private JLabel resistancesLabel;
    private JPanel speedsPanel;
    private JLabel speedsLabel;

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

    public CreaturesParametersView() {
        internationalizeCommonLabels();
        internationalizeEquipmentLabels();
        internationalizeTabs();
    }

    private void internationalizeTabs() {
        tabPane.setTitleAt(CREATURE_PARAMETERS_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "tab.creature.parameters"));
        tabPane.setTitleAt(CREATURE_EQUIPMENT_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "tab.creature.equipment"));
        tabPane.setTitleAt(CREATURE_SPELLS_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "tab.creature.spells"));
    }

    private void internationalizeCommonLabels() {
        characteristicsLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "characteristicsLabel"));
        resistancesLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "resistancesLabel"));
        speedsLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "speedsLabel"));
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
        return CreaturesParametersController.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
