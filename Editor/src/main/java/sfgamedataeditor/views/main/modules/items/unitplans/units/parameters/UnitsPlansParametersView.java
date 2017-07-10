package sfgamedataeditor.views.main.modules.items.unitplans.units.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.IconElement;
import sfgamedataeditor.common.viewconfigurations.item.unitplans.GUIElements;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;

import javax.swing.*;

@SuppressWarnings("unused")
public class UnitsPlansParametersView implements PresentableView {
    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.SELL_PRICE, DTOColumnNames = "copperSellingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel sellPricePanel;

    @GUIElement(GUIElementId = GUIElements.BUY_OUT_PRICE, DTOColumnNames = "copperBuyingPrice", DTOClass = ItemPriceParametersObject.class)
    private JPanel buyoutPricePanel;

    @GUIElement(GUIElementId = GUIElements.ITEM_SET, DTOColumnNames = "itemSetId", DTOClass = ItemPriceParametersObject.class)
    private JPanel itemSetPanel;

    @GUIElement(GUIElementId = GUIElements.UNIT, DTOColumnNames = "armyStatsId", DTOClass = ItemPriceParametersObject.class)
    private JPanel unitPanel;

    @IconElement
    private JLabel iconLabel;

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitsPlansParameterPresenter.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void localize() {

    }
}
