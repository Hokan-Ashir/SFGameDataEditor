package sfgamedataeditor.events.processing.strategies.content.modelcreators.creatures;

import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class CreatureRacesFromCreaturesModelCreator implements ModelCreator<ModulesModel, CreaturesModel> {

    @Override
    public ModulesModel createModel(CreaturesModel childModel) {
        // it's not important which object we choose, cause "getRaceIdByCreatureName" will correct identify race
        String creatureName = childModel.getParameter().getSubPanelsNames().iterator().next();
        int raceId = CreatureParametersTableService.INSTANCE.getRaceIdByCreatureName(creatureName);
        String raceName = I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(raceId));
        ModuleParameter parameter = new ModuleParameter(raceName);
        return new ModulesModel(parameter);
    }
}
