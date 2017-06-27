package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;

import java.util.List;

public class CreaturesModelParameter extends AbstractSubModuleParameter {

    public CreaturesModelParameter(List<ObjectTuple> panelTuples, ObjectTuple selectedObjectName) {
        super(panelTuples, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return CreaturesParametersView.class;
    }
}
