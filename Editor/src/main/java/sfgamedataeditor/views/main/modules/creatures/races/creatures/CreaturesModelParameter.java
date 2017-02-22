package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;

import java.util.Set;

public class CreaturesModelParameter extends AbstractSubModuleParameter {

    public CreaturesModelParameter(Set<String> objectNames, String selectedObjectName) {
        super(objectNames, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return CreaturesParametersView.class;
    }
}
