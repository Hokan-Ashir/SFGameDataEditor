package sfgamedataeditor.views.main.modules.items.miscellaneous.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.views.common.presenters.AbstractParametersPresenter;

import javax.swing.*;

public class MiscellaneousParametersPresenter extends AbstractParametersPresenter<MiscellaneousParametersModelParameter, MiscellaneousParametersView> {

    public MiscellaneousParametersPresenter(MiscellaneousParametersView view) {
        super(view);
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {
        MiscellaneousParametersModelParameter parameter = getModel().getParameter();
        ItemPriceParametersObject priceParametersObject = parameter.getPriceParametersObject();
        widget.getListener().updateWidgetValue(priceParametersObject);
    }
}
