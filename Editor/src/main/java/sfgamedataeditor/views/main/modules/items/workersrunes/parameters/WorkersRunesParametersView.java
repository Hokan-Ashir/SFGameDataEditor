package sfgamedataeditor.views.main.modules.items.workersrunes.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.IconElement;
import sfgamedataeditor.common.viewconfigurations.item.workerrunes.GUIElements;
import sfgamedataeditor.common.widgets.Disabled;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

@SuppressWarnings("unused")
public class WorkersRunesParametersView implements PresentableView {
    private static final int ITEM_PARAMETERS_TAB_INDEX = 0;
    private static final int UNIT_EQUIPMENT_TAB_INDEX = 1;

    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.SELL_PRICE, DTOColumnNames = "copperSellingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel sellPricePanel;

    @GUIElement(GUIElementId = GUIElements.BUY_OUT_PRICE, DTOColumnNames = "copperBuyingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel buyoutPricePanel;

    @GUIElement(GUIElementId = GUIElements.ITEM_SET, DTOColumnNames = "itemSetId", DTOClass = ItemPriceParametersObject.class)
    private JPanel itemSetPanel;

    @IconElement
    private JLabel iconLabel;

    @GUIElement(GUIElementId = GUIElements.LEVEL)
    private JPanel runeLevelPanel;

    @Disabled
    @GUIElement(GUIElementId = GUIElements.STATS_ID, DTOColumnNames = "statsId", DTOClass = CreatureParameterObject.class)
    private JPanel statsIdPanel;

    @Disabled
    @GUIElement(GUIElementId = GUIElements.RACE_ID, DTOColumnNames = "raceId", DTOClass = CreatureParameterObject.class)
    private JPanel raceIdPanel;

    @GUIElement(GUIElementId = GUIElements.UNIT_LEVEL, DTOColumnNames = "level", DTOClass = CreatureParameterObject.class)
    private JPanel unitLevelPanel;
    private JLabel characteristicsLabel;
    private JPanel characteristicsPanel;

    @GUIElement(GUIElementId = GUIElements.STRENGTH, DTOColumnNames = "strength", DTOClass = CreatureParameterObject.class)
    private JPanel strengthPanel;

    @GUIElement(GUIElementId = GUIElements.STAMINA, DTOColumnNames = "stamina", DTOClass = CreatureParameterObject.class)
    private JPanel staminaPanel;

    @GUIElement(GUIElementId = GUIElements.AGILITY, DTOColumnNames = "agility", DTOClass = CreatureParameterObject.class)
    private JPanel agilityPanel;

    @GUIElement(GUIElementId = GUIElements.DEXTERITY, DTOColumnNames = "dexterity", DTOClass = CreatureParameterObject.class)
    private JPanel dexterityPanel;

    @GUIElement(GUIElementId = GUIElements.WISDOM, DTOColumnNames = "wisdom", DTOClass = CreatureParameterObject.class)
    private JPanel wisdomPanel;

    @GUIElement(GUIElementId = GUIElements.INTELLIGENCE, DTOColumnNames = "intelligence", DTOClass = CreatureParameterObject.class)
    private JPanel intelligencePanel;

    @GUIElement(GUIElementId = GUIElements.CHARISMA, DTOColumnNames = "charisma", DTOClass = CreatureParameterObject.class)
    private JPanel charismaPanel;
    private JLabel resistancesLabel;
    private JPanel resistancesPanel;

    @GUIElement(GUIElementId = GUIElements.FIRE_RESISTANCE, DTOColumnNames = "fireResistance", DTOClass = CreatureParameterObject.class)
    private JPanel fireResistancePanel;

    @GUIElement(GUIElementId = GUIElements.ICE_RESISTANCE, DTOColumnNames = "iceResistance", DTOClass = CreatureParameterObject.class)
    private JPanel iceResistancePanel;

    @GUIElement(GUIElementId = GUIElements.MIND_RESISTANCE, DTOColumnNames = "mindResistance", DTOClass = CreatureParameterObject.class)
    private JPanel mindResistancePanel;

    @GUIElement(GUIElementId = GUIElements.BLACK_RESISTANCE, DTOColumnNames = "blackResistance", DTOClass = CreatureParameterObject.class)
    private JPanel blackResistancePanel;
    private JLabel speedsLabel;
    private JPanel speedsPanel;

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

    @GUIElement(GUIElementId = GUIElements.HEAD_ID, DTOColumnNames = "headId", DTOClass = CreatureParameterObject.class)
    private JPanel headIdPanel;

    @GUIElement(GUIElementId = GUIElements.EQUIPMENT_SLOTS_ID, DTOColumnNames = "equipmentSlotsId", DTOClass = CreatureParameterObject.class)
    private JPanel equipmentSlotsIdPanel;

    @GUIElement(GUIElementId = GUIElements.GENDER_AND_VULNERABILITY, DTOColumnNames = "genderAndVulnerability", DTOClass = CreatureParameterObject.class)
    private JPanel genderAndVulnerabilityPanel;
    private JTabbedPane tabPane;
    private JPanel itemParametersPanel;
    private JPanel unitParametersPanel;

    public WorkersRunesParametersView() {
        internationalizeCommonLabels();
    }

    private void internationalizeCommonLabels() {
        characteristicsLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.WORKERS_RUNES_GUI, "characteristicsLabel"));
        resistancesLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.WORKERS_RUNES_GUI, "resistancesLabel"));
        speedsLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.WORKERS_RUNES_GUI, "speedsLabel"));
    }

    private void internationalizeTabs() {
        tabPane.setTitleAt(ITEM_PARAMETERS_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.WORKERS_RUNES_GUI, "tab.item.parameters"));
        tabPane.setTitleAt(UNIT_EQUIPMENT_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.WORKERS_RUNES_GUI, "tab.unit.parameters"));
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return WorkersRunesParameterPresenter.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
