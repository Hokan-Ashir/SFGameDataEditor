package sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.item.armor.GUIElements;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class ArmorParametersView implements ControllableView {

    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.SELL_PRICE, DTOColumnNames = "copperSellingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel sellPricePanel;

    @GUIElement(GUIElementId = GUIElements.BUY_OUT_PRICE, DTOColumnNames = "copperBuyingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel buyoutPricePanel;

    @GUIElement(GUIElementId = GUIElements.STRENGTH, DTOColumnNames = "strength", DTOClass = ArmorParametersObject.class)
    private JPanel strengthPanel;

    @GUIElement(GUIElementId = GUIElements.STAMINA, DTOColumnNames = "stamina", DTOClass = ArmorParametersObject.class)
    private JPanel staminaPanel;

    @GUIElement(GUIElementId = GUIElements.AGILITY, DTOColumnNames = "agility", DTOClass = ArmorParametersObject.class)
    private JPanel agilityPanel;

    @GUIElement(GUIElementId = GUIElements.DEXTERITY, DTOColumnNames = "dexterity", DTOClass = ArmorParametersObject.class)
    private JPanel dexterityPanel;

    @GUIElement(GUIElementId = GUIElements.WISDOM, DTOColumnNames = "wisdom", DTOClass = ArmorParametersObject.class)
    private JPanel wisdomPanel;

    @GUIElement(GUIElementId = GUIElements.INTELLIGENCE, DTOColumnNames = "intelligence", DTOClass = ArmorParametersObject.class)
    private JPanel intelligencePanel;

    @GUIElement(GUIElementId = GUIElements.CHARISMA, DTOColumnNames = "charisma", DTOClass = ArmorParametersObject.class)
    private JPanel charismaPanel;

    @GUIElement(GUIElementId = GUIElements.MANA, DTOColumnNames = "mana", DTOClass = ArmorParametersObject.class)
    private JPanel manaPanel;

    @GUIElement(GUIElementId = GUIElements.ARMOR, DTOColumnNames = "armor", DTOClass = ArmorParametersObject.class)
    private JPanel armorPanel;

    @GUIElement(GUIElementId = GUIElements.FIRE_RESISTANCE, DTOColumnNames = "fireResistance", DTOClass = ArmorParametersObject.class)
    private JPanel fireResistancePanel;

    @GUIElement(GUIElementId = GUIElements.ICE_RESISTANCE, DTOColumnNames = "iceResistance", DTOClass = ArmorParametersObject.class)
    private JPanel iceResistancePanel;

    @GUIElement(GUIElementId = GUIElements.MIND_RESISTANCE, DTOColumnNames = "mindResistance", DTOClass = ArmorParametersObject.class)
    private JPanel mindResistancePanel;

    @GUIElement(GUIElementId = GUIElements.BLACK_RESISTANCE, DTOColumnNames = "blackResistance", DTOClass = ArmorParametersObject.class)
    private JPanel blackResistancePanel;

    @GUIElement(GUIElementId = GUIElements.WALK_SPEED, DTOColumnNames = "runSpeed", DTOClass = ArmorParametersObject.class)
    private JPanel runSpeedPanel;

    @GUIElement(GUIElementId = GUIElements.FIGHT_SPEED, DTOColumnNames = "fightSpeed", DTOClass = ArmorParametersObject.class)
    private JPanel fightSpeedPanel;

    @GUIElement(GUIElementId = GUIElements.CAST_SPEED, DTOColumnNames = "castSpeed", DTOClass = ArmorParametersObject.class)
    private JPanel castSpeedPanel;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_CLASS_SUBCLASS, DTOColumnNames = {"schoolRequirementClass", "subSchoolRequirementClass"}, DTOClass = ItemRequirementsObject.class)
    private JPanel requirementClassSubClassPanel;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_LEVEL, DTOColumnNames = "level", DTOClass = ItemRequirementsObject.class)
    private JPanel requirementLevelPanel;
    private JComboBox<String> requirementsComboBox;
    private JPanel characteristicsPanel;
    private JLabel characteristicsLabel;
    private JPanel resistancesPanel;
    private JLabel resistancesLabel;
    private JPanel speedsPanel;
    private JLabel speedsLabel;

    public ArmorParametersView() {
        internationalizeCommonLabels();
    }

    private void internationalizeCommonLabels() {
        characteristicsLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.ARMOR_GUI, "characteristicsLabel"));
        resistancesLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.ARMOR_GUI, "resistancesLabel"));
        speedsLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.ARMOR_GUI, "speedsLabel"));
    }

    public JComboBox<String> getRequirementsComboBox() {
        return requirementsComboBox;
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return ArmorParametersController.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
