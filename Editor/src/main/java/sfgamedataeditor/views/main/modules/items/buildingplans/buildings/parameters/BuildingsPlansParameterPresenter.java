package sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.views.common.AbstractParametersPresenter;

import javax.swing.*;

public class BuildingsPlansParameterPresenter extends AbstractParametersPresenter<BuildingsPlansParametersModelParameter, BuildingsPlansParametersView> {

    public BuildingsPlansParameterPresenter(BuildingsPlansParametersView view) {
        super(view);
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {
        BuildingsPlansParametersModelParameter parameter = getModel().getParameter();
        ItemPriceParametersObject priceParametersObject = parameter.getPriceParametersObject();
        widget.getListener().updateWidgetValue(priceParametersObject);
    }
}
