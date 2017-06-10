package sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.item.weapon.GUIElements;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

@SuppressWarnings("unused")
public class WeaponParametersView implements PresentableView {

    public static final Integer WEAPON_PARAMETERS_TAB_INDEX = 0;
    public static final Integer ARMOR_PARAMETERS_TAB_INDEX = 1;

    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.SELL_PRICE, DTOColumnNames = "copperSellingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel sellPricePanel;

    @GUIElement(GUIElementId = GUIElements.BUY_OUT_PRICE, DTOColumnNames = "copperBuyingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel buyoutPricePanel;

    @GUIElement(GUIElementId = GUIElements.ITEM_EFFECT, DTOColumnNames = "effectNumber", DTOClass = ItemSpellEffectsObject.class)
    private JPanel itemEffectPanel;

    @GUIElement(GUIElementId = GUIElements.MIN_DAMAGE, DTOColumnNames = "minDamage", DTOClass = WeaponParametersObject.class)
    private JPanel minDamagePanel;

    @GUIElement(GUIElementId = GUIElements.MAX_DAMAGE, DTOColumnNames = "maxDamage", DTOClass = WeaponParametersObject.class)
    private JPanel maxDamagePanel;

    @GUIElement(GUIElementId = GUIElements.MIN_RANGE, DTOColumnNames = "minRange", DTOClass = WeaponParametersObject.class)
    private JPanel minRangePanel;

    @GUIElement(GUIElementId = GUIElements.MAX_RANGE, DTOColumnNames = "maxRange", DTOClass = WeaponParametersObject.class)
    private JPanel maxRangePanel;

    @GUIElement(GUIElementId = GUIElements.SPEED, DTOColumnNames = "speed", DTOClass = WeaponParametersObject.class)
    private JPanel speedPanel;

    @GUIElement(GUIElementId = GUIElements.TYPE, DTOColumnNames = "type", DTOClass = WeaponParametersObject.class)
    private JPanel typePanel;

    @GUIElement(GUIElementId = GUIElements.MATERIAL, DTOColumnNames = "material", DTOClass = WeaponParametersObject.class)
    private JPanel materialPanel;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_CLASS_SUBCLASS, DTOColumnNames = {"schoolRequirementClass", "subSchoolRequirementClass"}, DTOClass = ItemRequirementsObject.class)
    private JPanel requirementClassSubClassPanel;

    @GUIElement(GUIElementId = GUIElements.REQUIREMENT_LEVEL, DTOColumnNames = "level", DTOClass = ItemRequirementsObject.class)
    private JPanel requirementLevelPanel;

    private JComboBox<String> itemRequirementsComboBox;
    private JComboBox<String> effectsComboBox;

    @GUIElement(GUIElementId = GUIElements.ITEM_SET, DTOColumnNames = "itemSetId", DTOClass = ItemPriceParametersObject.class)
    private JPanel itemSetPanel;
    private JTabbedPane tabPane;
    private JPanel weaponParametersPanel;
    private JPanel armorParametersPanel;
    private JLabel characteristicsLabel;
    private JPanel resistancesPanel;

    @GUIElement(GUIElementId = GUIElements.FIRE_RESISTANCE, DTOColumnNames = "fireResistance", DTOClass = ArmorParametersObject.class)
    private JPanel fireResistancePanel;

    @GUIElement(GUIElementId = GUIElements.ICE_RESISTANCE, DTOColumnNames = "iceResistance", DTOClass = ArmorParametersObject.class)
    private JPanel iceResistancePanel;

    @GUIElement(GUIElementId = GUIElements.MIND_RESISTANCE, DTOColumnNames = "mindResistance", DTOClass = ArmorParametersObject.class)
    private JPanel mindResistancePanel;

    @GUIElement(GUIElementId = GUIElements.BLACK_RESISTANCE, DTOColumnNames = "blackResistance", DTOClass = ArmorParametersObject.class)
    private JPanel blackResistancePanel;

    private JLabel resistancesLabel;
    private JLabel speedsLabel;
    private JPanel characteristicsPanel;

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

    private JPanel speedsPanel;

    @GUIElement(GUIElementId = GUIElements.RUN_SPEED, DTOColumnNames = "runSpeed", DTOClass = ArmorParametersObject.class)
    private JPanel runSpeedPanel;

    @GUIElement(GUIElementId = GUIElements.FIGHT_SPEED, DTOColumnNames = "fightSpeed", DTOClass = ArmorParametersObject.class)
    private JPanel fightSpeedPanel;

    @GUIElement(GUIElementId = GUIElements.CAST_SPEED, DTOColumnNames = "castSpeed", DTOClass = ArmorParametersObject.class)
    private JPanel castSpeedPanel;

    @GUIElement(GUIElementId = GUIElements.ARMOR, DTOColumnNames = "armor", DTOClass = ArmorParametersObject.class)
    private JPanel armorPanel;

    @GUIElement(GUIElementId = GUIElements.MANA, DTOColumnNames = "mana", DTOClass = ArmorParametersObject.class)
    private JPanel manaPanel;

    public WeaponParametersView() {
        internationalizeCommonLabels();
        internationalizeTabs();
    }

    private void internationalizeCommonLabels() {
        characteristicsLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "characteristicsLabel"));
        resistancesLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "resistancesLabel"));
        speedsLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "speedsLabel"));
    }

    private void internationalizeTabs() {
        tabPane.setTitleAt(WEAPON_PARAMETERS_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "tab.weapon.parameters"));
        tabPane.setTitleAt(ARMOR_PARAMETERS_TAB_INDEX, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "tab.armor.parameters"));
    }

    public JTabbedPane getTabPane() {
        return tabPane;
    }

    public JPanel getItemEffectPanel() {
        return itemEffectPanel;
    }

    public JComboBox<String> getEffectsComboBox() {
        return effectsComboBox;
    }

    public JComboBox<String> getItemRequirementsComboBox() {
        return itemRequirementsComboBox;
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return WeaponParametersPresenter.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
