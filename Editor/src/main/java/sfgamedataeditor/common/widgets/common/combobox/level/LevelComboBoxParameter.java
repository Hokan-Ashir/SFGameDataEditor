package sfgamedataeditor.common.widgets.common.combobox.level;

import java.util.Set;

public class LevelComboBoxParameter {
    private final int selectedLevel;
    private final Set<Integer> levels;

    public LevelComboBoxParameter(int selectedLevel, Set<Integer> levels) {
        this.selectedLevel = selectedLevel;
        this.levels = levels;
    }

    int getSelectedLevel() {
        return selectedLevel;
    }

    Set<Integer> getLevels() {
        return levels;
    }
}
