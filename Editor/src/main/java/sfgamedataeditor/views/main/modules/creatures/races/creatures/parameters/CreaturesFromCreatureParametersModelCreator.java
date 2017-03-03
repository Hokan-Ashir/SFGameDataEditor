package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModelParameter;

import java.util.Set;

public class CreaturesFromCreatureParametersModelCreator implements ModelCreator<CreaturesModel, CreaturesParametersModel> {

    @Override
    public CreaturesModel createModel(CreaturesParametersModel childModel) {
        Integer raceId = childModel.getParameter().getCreatureParameterObject().raceId;
        Set<String> creatureNames = CreatureCommonParametersTableService.INSTANCE.getCreatureNamesByRaceId(raceId);

        String selectedCreatureName = childModel.getParameter().getCreatureCommonParameterObject().name;
        CreaturesModelParameter parameter = new CreaturesModelParameter(creatureNames, selectedCreatureName);
        return new CreaturesModel(parameter);
    }
}
