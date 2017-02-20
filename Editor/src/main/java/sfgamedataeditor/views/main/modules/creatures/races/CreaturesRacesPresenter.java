package sfgamedataeditor.views.main.modules.creatures.races;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModelParameter;

import java.util.Set;

public class CreaturesRacesPresenter extends AbstractModulesPresenter<ModuleParameter, AbstractRacesView, CreaturesModel> {

    public CreaturesRacesPresenter(AbstractRacesView view) {
        super(view);
    }

    @Override
    protected CreaturesModel createModel() {
        String selectedRaceName = getView().getSelectedModuleValue();
        Set<String> creatureNames = CreatureCommonParametersTableService.INSTANCE.getCreatureNamesByRaceName(selectedRaceName);
        CreaturesModelParameter parameter = new CreaturesModelParameter(creatureNames, null);
        return new CreaturesModel(parameter);
    }
}
