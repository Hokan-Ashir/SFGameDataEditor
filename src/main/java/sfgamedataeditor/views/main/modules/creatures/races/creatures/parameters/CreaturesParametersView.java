package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.creatureparameters.GUIElements;
import sfgamedataeditor.common.widgets.Disabled;
import sfgamedataeditor.database.creatures.CreatureParameterObject;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class CreaturesParametersView implements ControllableView {

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

    public CreaturesParametersView() {
        characteristicsLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "characteristicsLabel"));
        resistancesLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "resistancesLabel"));
        speedsLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "speedsLabel"));
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
