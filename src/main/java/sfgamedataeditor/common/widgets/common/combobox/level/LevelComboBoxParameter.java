package sfgamedataeditor.common.widgets.common.combobox.level;

import java.util.Set;

public class LevelComboBoxParameter {
    private final int selectedLevel;
    private final Set<Integer> spellLevels;

    public LevelComboBoxParameter(int selectedLevel, Set<Integer> spellLevels) {
        this.selectedLevel = selectedLevel;
        this.spellLevels = spellLevels;
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }

    public Set<Integer> getSpellLevels() {
        return spellLevels;
    }
}
