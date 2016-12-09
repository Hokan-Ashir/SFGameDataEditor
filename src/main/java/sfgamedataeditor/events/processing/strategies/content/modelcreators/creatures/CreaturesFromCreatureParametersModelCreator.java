package sfgamedataeditor.events.processing.strategies.content.modelcreators.creatures;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModelParameter;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModel;

import java.util.List;

public class CreaturesFromCreatureParametersModelCreator implements ModelCreator<CreaturesModel, CreaturesParametersModel> {

    @Override
    public CreaturesModel createModel(CreaturesParametersModel childModel) {
        Integer raceId = childModel.getParameter().getCreatureParameterObject().raceId;
        List<String> creatureNames = CreatureCommonParametersTableService.INSTANCE.getCreatureNamesByRaceId(raceId);

        Integer statsId = childModel.getParameter().getCreatureParameterObject().statsId;
        String selectedCreatureName = CreatureCommonParametersTableService.INSTANCE.getCreatureNameById(statsId);
        CreaturesModelParameter parameter = new CreaturesModelParameter(creatureNames, selectedCreatureName);
        return new CreaturesModel(parameter);
    }
}
