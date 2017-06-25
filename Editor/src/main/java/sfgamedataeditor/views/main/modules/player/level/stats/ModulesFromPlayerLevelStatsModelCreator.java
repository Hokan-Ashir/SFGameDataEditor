package sfgamedataeditor.views.main.modules.player.level.stats;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ModulesFromPlayerLevelStatsModelCreator implements ModelCreator<ModulesModel, PlayerLevelStatsModel> {

    @Override
    public ModulesModel createModel(PlayerLevelStatsModel childModel) {
        ModuleParameter parameter = new ModuleParameter(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "playerStats"));
        return new ModulesModel(parameter);
    }
}
