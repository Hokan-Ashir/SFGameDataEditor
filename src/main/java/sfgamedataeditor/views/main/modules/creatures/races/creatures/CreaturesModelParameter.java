package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.mvc.objects.SubModuleParameter;

import java.util.List;

public class CreaturesModelParameter implements SubModuleParameter {
    private final List<String> creatureNames;
    private final String selectedCreatureName;

    public CreaturesModelParameter(List<String> creatureNames, String selectedCreatureName) {
        this.creatureNames = creatureNames;
        this.selectedCreatureName = selectedCreatureName;
    }

    public List<String> getCreatureNames() {
        return creatureNames;
    }

    @Override
    public String getSelectedModuleName() {
        return selectedCreatureName;
    }
}
