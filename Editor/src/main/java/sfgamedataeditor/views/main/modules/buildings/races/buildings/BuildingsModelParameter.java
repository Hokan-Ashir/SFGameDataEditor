package sfgamedataeditor.views.main.modules.buildings.races.buildings;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersView;

import java.util.Set;

public class BuildingsModelParameter extends AbstractSubModuleParameter {

    public BuildingsModelParameter(Set<String> objectNames, String selectedObjectName) {
        super(objectNames, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return BuildingsParametersView.class;
    }
}
