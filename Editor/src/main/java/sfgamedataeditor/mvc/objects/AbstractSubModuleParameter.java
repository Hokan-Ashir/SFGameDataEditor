package sfgamedataeditor.mvc.objects;

import sfgamedataeditor.views.common.SubViewPanelTuple;

import java.util.List;

public abstract class AbstractSubModuleParameter extends IconableParameter {
    private final List<SubViewPanelTuple> panelTuples;
    private final String selectedModuleName;

    protected AbstractSubModuleParameter(List<SubViewPanelTuple> panelTuples, String selectedObjectName) {
        super(null);
        this.panelTuples = panelTuples;
        this.selectedModuleName = selectedObjectName;
    }

    public String getSelectedModuleName() {
        return selectedModuleName;
    }

    public List<SubViewPanelTuple> getSubViewPanelTuples() {
        return panelTuples;
    }

    public abstract Class<? extends PresentableView> getSubPanelsViewClass();
}
