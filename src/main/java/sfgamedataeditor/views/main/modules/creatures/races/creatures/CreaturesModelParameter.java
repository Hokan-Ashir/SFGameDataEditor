package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import java.util.List;

public class CreaturesModelParameter {
    private final List<String> creatureNames;
    private final String selectedCreatureName;

    public CreaturesModelParameter(List<String> creatureNames, String selectedCreatureName) {
        this.creatureNames = creatureNames;
        this.selectedCreatureName = selectedCreatureName;
    }

    public List<String> getCreatureNames() {
        return creatureNames;
    }

    public String getSelectedCreatureName() {
        return selectedCreatureName;
    }
}
