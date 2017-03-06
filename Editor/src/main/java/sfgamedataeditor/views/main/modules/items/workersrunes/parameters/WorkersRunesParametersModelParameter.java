package sfgamedataeditor.views.main.modules.items.workersrunes.parameters;

import javax.swing.*;

public class WorkersRunesParametersModelParameter {
    private final String runeName;
    private Integer runeLevel;
    private final Icon icon;

    public WorkersRunesParametersModelParameter(String runeName,
                                                Integer runeLevel,
                                                Icon icon) {
        this.runeName = runeName;
        this.runeLevel = runeLevel;
        this.icon = icon;
    }

    public Integer getRuneLevel() {
        return runeLevel;
    }

    public void setRuneLevel(Integer runeLevel) {
        this.runeLevel = runeLevel;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getRuneName() {
        return runeName;
    }
}
