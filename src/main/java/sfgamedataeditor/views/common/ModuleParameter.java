package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.mvc.objects.SubModuleParameter;

import java.util.Set;

public class ModuleParameter implements SubModuleParameter {
    private final String moduleName;

    public ModuleParameter(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String getSelectedModuleName() {
        return moduleName;
    }

    @Override
    public Set<String> getSubPanelsNames() {
        return null;
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return null;
    }
}
