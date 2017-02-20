package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.IconElement;
import sfgamedataeditor.common.viewconfigurations.item.scrolls.GUIElements;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;

import javax.swing.*;

public class SpellScrollsParametersView implements PresentableView {

    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.SELL_PRICE, DTOColumnNames = "copperSellingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel sellPricePanel;

    @GUIElement(GUIElementId = GUIElements.BUY_OUT_PRICE, DTOColumnNames = "copperBuyingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel buyoutPricePanel;

    @GUIElement(GUIElementId = GUIElements.ITEM_EFFECT, DTOColumnNames = "effectNumber", DTOClass = ItemSpellEffectsObject.class)
    private JPanel itemEffectPanel;

    @IconElement
    private JLabel iconLabel;

    @GUIElement(GUIElementId = GUIElements.ITEM_SET, DTOColumnNames = "itemSetId", DTOClass = ItemPriceParametersObject.class)
    private JPanel itemSetPanel;

    @GUIElement(GUIElementId = GUIElements.LEVEL)
    private JPanel levelPanel;

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SpellScrollsParametersPresenter.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
