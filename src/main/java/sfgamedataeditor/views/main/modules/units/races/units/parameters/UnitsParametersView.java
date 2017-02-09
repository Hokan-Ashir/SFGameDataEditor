package sfgamedataeditor.views.main.modules.units.races.units.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.IconElement;
import sfgamedataeditor.common.viewconfigurations.unit.parameters.GUIElements;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.production.buildings.CreatureBuildingsObject;
import sfgamedataeditor.database.creatures.production.resources.CreatureResourcesObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class UnitsParametersView implements PresentableView {

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

    // assuming creature have 3 spells at most, though ordinary it has 2 top
    @GUIElement(GUIElementId = GUIElements.SPELL1, DTOColumnNames = "spellNumber", DTOClass = CreatureSpellObject.class)
    private JPanel spell1Panel;

    @GUIElement(GUIElementId = GUIElements.SPELL2, DTOColumnNames = "spellNumber", DTOClass = CreatureSpellObject.class)
    private JPanel spell2Panel;

    @GUIElement(GUIElementId = GUIElements.SPELL3, DTOColumnNames = "spellNumber", DTOClass = CreatureSpellObject.class)
    private JPanel spell3Panel;

    @GUIElement(GUIElementId = GUIElements.RESOURCE1, DTOColumnNames = {"resourceId", "resourceAmount"}, DTOClass = CreatureResourcesObject.class)
    private JPanel resource1Panel;

    @GUIElement(GUIElementId = GUIElements.RESOURCE2, DTOColumnNames = {"resourceId", "resourceAmount"}, DTOClass = CreatureResourcesObject.class)
    private JPanel resource2Panel;

    @GUIElement(GUIElementId = GUIElements.RESOURCE3, DTOColumnNames = {"resourceId", "resourceAmount"}, DTOClass = CreatureResourcesObject.class)
    private JPanel resource3Panel;
    private JPanel parametersPanel;
    private JLabel parametersLabel;
    private JLabel resourcesLabel;
    private JPanel resourcesPanel;
    private JLabel requiredBuildingsLabel;
    private JPanel requiredBuildingsPanel;

    @GUIElement(GUIElementId = GUIElements.BUILDING1, DTOColumnNames = "buildingId", DTOClass = CreatureBuildingsObject.class)
    private JPanel requiredBuilding1Panel;

    @GUIElement(GUIElementId = GUIElements.BUILDING2, DTOColumnNames = "buildingId", DTOClass = CreatureBuildingsObject.class)
    private JPanel requiredBuilding2Panel;

    @GUIElement(GUIElementId = GUIElements.BUILDING3, DTOColumnNames = "buildingId", DTOClass = CreatureBuildingsObject.class)
    private JPanel requiredBuilding3Panel;

    @IconElement
    private JLabel iconLabel;

    public UnitsParametersView() {
        internationalizeCommonLabels();
        internationalizeEquipmentLabels();
        internationalizeTabs();
    }

    private void internationalizeCommonLabels() {
        parametersLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "creature.parameters.label"));
        resourcesLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "creature.resources.label"));
        requiredBuildingsLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "creature.buildings.label"));
    }

    private void internationalizeTabs() {
        tabPane.setTitleAt(CREATURE_PARAMETERS_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "tab.creature.parameters"));
        tabPane.setTitleAt(CREATURE_EQUIPMENT_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "tab.creature.equipment"));
        tabPane.setTitleAt(CREATURE_SPELLS_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "tab.creature.spells"));
    }

    private void internationalizeEquipmentLabels() {
        headSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "equipment.head.slot.label"));
        leftHandWeaponSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "equipment.left.hand.slot.label"));
        rightHandWeaponSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "equipment.right.hand.slot.label"));
        chestSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "equipment.chest.slot.label"));
        leftRingSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "equipment.left.ring.slot.label"));
        rightRingSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "equipment.right.ring.slot.label"));
        legsSlotLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "equipment.legs.slot.label"));
    }

    public JTabbedPane getTabPane() {
        return tabPane;
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitsParametersPresenter.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
