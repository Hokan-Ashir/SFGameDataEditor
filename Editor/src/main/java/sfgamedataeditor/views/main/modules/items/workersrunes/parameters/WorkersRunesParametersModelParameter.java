package sfgamedataeditor.views.main.modules.items.workersrunes.parameters;

import sfgamedataeditor.mvc.objects.LevelableParameter;

import javax.swing.*;
import java.util.Map;

public class WorkersRunesParametersModelParameter extends LevelableParameter {
    private final String runeName;
    private final Map<Integer, Integer> runeLevelToItemIdMap;

    public WorkersRunesParametersModelParameter(String runeName,
                                                Integer runeLevel,
                                                Map<Integer, Integer> runeLevelToItemIdMap,
                                                Icon icon) {
        super(runeLevel, icon);
        this.runeName = runeName;
        this.runeLevelToItemIdMap = runeLevelToItemIdMap;
    }

    public Map<Integer, Integer> getRuneLevelToItemIdMap() {
        return runeLevelToItemIdMap;
    }

    public String getRuneName() {
        return runeName;
    }
}
