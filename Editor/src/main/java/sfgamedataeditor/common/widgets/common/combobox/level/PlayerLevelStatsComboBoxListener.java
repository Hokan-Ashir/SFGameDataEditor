package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.player.level.stats.PlayerLevelStatsView;

import java.lang.reflect.Field;

public class PlayerLevelStatsComboBoxListener extends AbstractLevelComboBoxListener {

    public PlayerLevelStatsComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
        super(widget, mappedField);
    }

    @Override
    protected Class<? extends PresentableView> getViewClass() {
        return PlayerLevelStatsView.class;
    }
}
