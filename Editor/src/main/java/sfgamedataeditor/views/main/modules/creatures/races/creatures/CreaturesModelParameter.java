package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.mvc.objects.SubModuleParameter;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;

import java.util.Set;

public class CreaturesModelParameter implements SubModuleParameter {
    private final Set<String> creatureNames;
    private final String selectedCreatureName;

    public CreaturesModelParameter(Set<String> creatureNames, String selectedCreatureName) {
        this.creatureNames = creatureNames;
        this.selectedCreatureName = selectedCreatureName;
    }

    @Override
    public String getSelectedModuleName() {
        return selectedCreatureName;
    }

    @Override
    public Set<String> getSubPanelsNames() {
        return creatureNames;
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return CreaturesParametersView.class;
    }
}
