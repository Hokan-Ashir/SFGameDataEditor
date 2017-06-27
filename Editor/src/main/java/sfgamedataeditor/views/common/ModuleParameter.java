package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;

public class ModuleParameter extends AbstractSubModuleParameter {

    public ModuleParameter(ObjectTuple moduleName) {
        super(null, moduleName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return null;
    }
}
