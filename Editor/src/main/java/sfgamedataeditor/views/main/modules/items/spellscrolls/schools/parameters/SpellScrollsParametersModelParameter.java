package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.mvc.objects.LevelableParameter;
import sfgamedataeditor.views.utility.Pair;

import javax.swing.*;
import java.util.Map;

public class SpellScrollsParametersModelParameter extends LevelableParameter {
    private final String scrollBaseName;
    private final Map<Integer, Pair<Integer, Integer>> levelToItemsIdMap;

    public SpellScrollsParametersModelParameter(String scrollBaseName, Integer scrollLevel, Map<Integer, Pair<Integer, Integer>> levelToItemsIdMap, Icon icon) {
        super(scrollLevel, icon);
        this.scrollBaseName = scrollBaseName;
        this.levelToItemsIdMap = levelToItemsIdMap;
    }

    public String getScrollBaseName() {
        return scrollBaseName;
    }

    public Map<Integer, Pair<Integer, Integer>> getLevelToItemsIdMap() {
        return levelToItemsIdMap;
    }
}
