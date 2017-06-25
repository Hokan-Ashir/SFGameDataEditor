package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModelParameter;

import java.util.List;

public class CreaturesFromCreatureParametersModelCreator implements ModelCreator<CreaturesModel, CreaturesParametersModel> {

    @Override
    public CreaturesModel createModel(CreaturesParametersModel childModel) {
        Integer raceId = childModel.getParameter().getCreatureParameterObject().raceId;
        List<SubViewPanelTuple> creatureNames = CreatureCommonParametersTableService.INSTANCE.getCreatureNamesByRaceId(raceId);

        Integer nameId = childModel.getParameter().getCreatureCommonParameterObject().nameId;
        String selectedCreatureName = TextTableService.INSTANCE.getObjectName(nameId);
        CreaturesModelParameter parameter = new CreaturesModelParameter(creatureNames, selectedCreatureName);
        return new CreaturesModel(parameter);
    }
}
