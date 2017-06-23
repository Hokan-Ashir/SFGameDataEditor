package sfgamedataeditor.mvc.objects;

import java.util.Set;

public abstract class AbstractSubModuleParameter extends IconableParameter {
    private final Set<String> subPanelsNames;
    private final String selectedModuleName;

    protected AbstractSubModuleParameter(Set<String> objectNames, String selectedObjectName) {
        super(null);
        this.subPanelsNames = objectNames;
        this.selectedModuleName = selectedObjectName;
    }

    public String getSelectedModuleName() {
        return selectedModuleName;
    }

    public Set<String> getSubPanelsNames() {
        return subPanelsNames;
    }

    public abstract Class<? extends PresentableView> getSubPanelsViewClass();
}
