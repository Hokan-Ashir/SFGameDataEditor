package sfgamedataeditor.mvc.objects;

import java.util.Set;

public abstract class AbstractSubModuleParameter {
    private final Set<String> objectNames;
    private final String selectedObjectName;

    protected AbstractSubModuleParameter(Set<String> objectNames, String selectedObjectName) {
        this.objectNames = objectNames;
        this.selectedObjectName = selectedObjectName;
    }

    public String getSelectedModuleName() {
        return selectedObjectName;
    }

    public Set<String> getSubPanelsNames() {
        return objectNames;
    }

    public abstract Class<? extends PresentableView> getSubPanelsViewClass();
}
