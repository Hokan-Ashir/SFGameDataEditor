package sfgamedataeditor.views.main.modules.items.workersrunes.parameters;

import sfgamedataeditor.mvc.objects.LevelableParameter;

import javax.swing.*;

public class WorkersRunesParametersModelParameter extends LevelableParameter {
    private final String runeName;
    private final Icon icon;

    public WorkersRunesParametersModelParameter(String runeName,
                                                Integer runeLevel,
                                                Icon icon) {
        super(runeLevel);
        this.runeName = runeName;
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getRuneName() {
        return runeName;
    }
}
