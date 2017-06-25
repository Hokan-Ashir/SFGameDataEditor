package sfgamedataeditor.views.main.modules.player.level.stats;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.player.level.stats.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.common.combobox.level.LevelComboBoxParameter;
import sfgamedataeditor.database.player.level.stats.PlayerLevelStatsObject;
import sfgamedataeditor.database.player.level.stats.PlayerLevelStatsTableService;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.presenters.AbstractParametersPresenter;

import javax.swing.*;
import java.util.Set;

public class PlayerLevelStatsPresenter extends AbstractParametersPresenter<PlayerLevelStatsModelParameter, PlayerLevelStatsView> {

    private static final Set<Integer> POSSIBLE_PLAYER_LEVELS = PlayerLevelStatsTableService.INSTANCE.getPlayerStatsLevels();

    public PlayerLevelStatsPresenter(PlayerLevelStatsView view) {
        super(view);
    }

    @Override
    protected void updateWidget(AbstractWidget widget, GUIElement annotation, JPanel panel) {
        Model<PlayerLevelStatsModelParameter> model = getModel();
        if (model == null) {
            model = new PlayerLevelStatsModel(new PlayerLevelStatsModelParameter(1, null));
            setModel(model);
        }

        Integer selectedLevel = model.getParameter().getLevel();
        PlayerLevelStatsObject object = PlayerLevelStatsTableService.INSTANCE.getObjectByLevel(selectedLevel);

        int guiElementId = annotation.GUIElementId();
        if (guiElementId != GUIElements.LEVEL) {
            widget.getListener().updateWidgetValue(object);
        } else {
            LevelComboBoxParameter levelComboBoxParameter = new LevelComboBoxParameter(selectedLevel, POSSIBLE_PLAYER_LEVELS);
            widget.getListener().updateWidgetValue(levelComboBoxParameter);
        }
    }
}
