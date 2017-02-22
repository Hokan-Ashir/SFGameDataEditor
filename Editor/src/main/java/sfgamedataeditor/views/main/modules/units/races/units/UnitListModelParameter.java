package sfgamedataeditor.views.main.modules.units.races.units;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersView;

import java.util.Set;

public class UnitListModelParameter extends AbstractSubModuleParameter {

    public UnitListModelParameter(Set<String> objectNames, String selectedObjectName) {
        super(objectNames, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return UnitsParametersView.class;
    }
}
