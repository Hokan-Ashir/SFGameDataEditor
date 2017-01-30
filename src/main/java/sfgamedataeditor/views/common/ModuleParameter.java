package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.objects.SubModuleParameter;

public class ModuleParameter implements SubModuleParameter {
    private final String moduleName;

    public ModuleParameter(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String getSelectedModuleName() {
        return moduleName;
    }
}
