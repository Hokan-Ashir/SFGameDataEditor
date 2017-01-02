package sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.item.weapon.GUIElements;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;

import javax.swing.*;

public class WeaponParametersView implements ControllableView {

    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.SELL_PRICE, DTOColumnNames = "copperSellingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel sellPricePanel;

    @GUIElement(GUIElementId = GUIElements.BUY_OUT_PRICE, DTOColumnNames = "copperBuyingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel buyoutPricePanel;

    // TODO item may have multiple effects with help of "itemEffectNumber"
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
    public Class<? extends AbstractController> getControllerClass() {
        return WeaponParametersController.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
