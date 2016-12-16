package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.item.scrolls.GUIElements;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;

import javax.swing.*;

public class SpellScrollsParametersView implements ControllableView {

    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.SELL_PRICE, DTOColumnNames = "copperSellingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel sellPricePanel;

    @GUIElement(GUIElementId = GUIElements.BUY_OUT_PRICE, DTOColumnNames = "copperBuyingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel buyoutPricePanel;

    @GUIElement(GUIElementId = GUIElements.ITEM_EFFECT, DTOColumnNames = "effectNumber", DTOClass = ItemSpellEffectsObject.class)
    private JPanel itemEffectPanel;

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return SpellScrollsParametersController.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}