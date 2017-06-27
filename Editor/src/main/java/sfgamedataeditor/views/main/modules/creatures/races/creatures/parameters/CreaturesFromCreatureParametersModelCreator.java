package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModelParameter;

import java.util.List;

public class CreaturesFromCreatureParametersModelCreator implements ModelCreator<CreaturesModel, CreaturesParametersModel> {

    @Override
    public CreaturesModel createModel(CreaturesParametersModel childModel) {
        Integer raceId = childModel.getParameter().getCreatureParameterObject().raceId;
        List<ObjectTuple> creatureNames = CreatureCommonParametersTableService.INSTANCE.getCreatureNamesByRaceId(raceId);

        CreaturesCommonParameterObject object = childModel.getParameter().getCreatureCommonParameterObject();
        ObjectTuple objectTuple = TextTableService.INSTANCE.getObjectTuple(object.nameId, object.creatureId);
        CreaturesModelParameter parameter = new CreaturesModelParameter(creatureNames, objectTuple);
        return new CreaturesModel(parameter);
    }
}
