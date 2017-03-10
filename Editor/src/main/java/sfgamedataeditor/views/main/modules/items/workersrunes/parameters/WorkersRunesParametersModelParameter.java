package sfgamedataeditor.views.main.modules.items.workersrunes.parameters;

import sfgamedataeditor.mvc.objects.LevelableParameter;

import javax.swing.*;

public class WorkersRunesParametersModelParameter extends LevelableParameter {
    private final String runeName;

    public WorkersRunesParametersModelParameter(String runeName,
                                                Integer runeLevel,
                                                Icon icon) {
        super(runeLevel, icon);
        this.runeName = runeName;
    }

    public String getRuneName() {
        return runeName;
    }
}
