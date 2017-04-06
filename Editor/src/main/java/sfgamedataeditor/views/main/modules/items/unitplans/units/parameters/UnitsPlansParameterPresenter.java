package sfgamedataeditor.views.main.modules.items.unitplans.units.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.views.common.presenters.AbstractParametersPresenter;

import javax.swing.*;

public class UnitsPlansParameterPresenter extends AbstractParametersPresenter<UnitsPlansParametersModelParameter, UnitsPlansParametersView> {

    public UnitsPlansParameterPresenter(UnitsPlansParametersView view) {
        super(view);
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {
        UnitsPlansParametersModelParameter parameter = getModel().getParameter();
        ItemPriceParametersObject priceParametersObject = parameter.getPriceParametersObject();
        widget.getListener().updateWidgetValue(priceParametersObject);
    }
}
