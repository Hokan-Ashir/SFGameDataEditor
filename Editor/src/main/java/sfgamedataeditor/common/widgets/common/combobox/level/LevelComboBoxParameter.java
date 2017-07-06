package sfgamedataeditor.common.widgets.common.combobox.level;

import java.util.Set;

public class LevelComboBoxParameter {
    private final Integer selectedLevel;
    private final Set<Integer> levels;

    public LevelComboBoxParameter(Integer selectedLevel, Set<Integer> levels) {
        this.selectedLevel = selectedLevel;
        this.levels = levels;
    }

    Integer getSelectedLevel() {
        return selectedLevel;
    }

    Set<Integer> getLevels() {
        return levels;
    }
}
