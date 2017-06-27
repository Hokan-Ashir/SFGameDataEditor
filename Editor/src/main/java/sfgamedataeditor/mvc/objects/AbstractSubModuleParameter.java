package sfgamedataeditor.mvc.objects;

import sfgamedataeditor.views.common.ObjectTuple;

import java.util.List;

public abstract class AbstractSubModuleParameter extends IconableParameter {
    private final List<ObjectTuple> panelTuples;
    // TODO replace with objectTuple
    private final ObjectTuple selectedModuleName;

    protected AbstractSubModuleParameter(List<ObjectTuple> panelTuples, ObjectTuple selectedObjectName) {
        super(null);
        this.panelTuples = panelTuples;
        this.selectedModuleName = selectedObjectName;
    }

    public ObjectTuple getSelectedModuleName() {
        return selectedModuleName;
    }

    public List<ObjectTuple> getSubViewPanelTuples() {
        return panelTuples;
    }

    public abstract Class<? extends PresentableView> getSubPanelsViewClass();
}
