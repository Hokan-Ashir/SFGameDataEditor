package sfgamedataeditor.views.main.modules.buildings.races.buildings;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersView;

import java.util.List;

public class BuildingsModelParameter extends AbstractSubModuleParameter {

    public BuildingsModelParameter(List<ObjectTuple> panelTuples, ObjectTuple selectedObjectName) {
        super(panelTuples, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return BuildingsParametersView.class;
    }
}
