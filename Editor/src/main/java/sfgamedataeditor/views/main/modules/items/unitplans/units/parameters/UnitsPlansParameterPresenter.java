package sfgamedataeditor.views.main.modules.items.unitplans.units.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.views.common.AbstractParametersPresenter;

import javax.swing.*;

public class UnitsPlansParameterPresenter extends AbstractParametersPresenter<UnitsPlansParametersModelParameter, UnitsPlansParametersView> {

    private static final Logger LOGGER = Logger.getLogger(UnitsPlansParameterPresenter.class);

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
