package sfgamedataeditor.views.main.modules.creatures.races;

import sfgamedataeditor.database.creatures.CreatureParameterObject;
import sfgamedataeditor.database.creatures.CreatureParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModelParameter;

import java.util.List;

public class CreaturesRacesController extends AbstractModulesController<ModuleParameter, AbstractRacesView, CreaturesModel> {

    public CreaturesRacesController(AbstractRacesView view) {
        super(view);
    }

    @Override
    protected CreaturesModel createModel() {
        String selectedRaceName = getView().getSelectedModuleValue();
        List<CreatureParameterObject> creaturesByRaceIdName = CreatureParametersTableService.INSTANCE.getCreaturesByRaceIdName(selectedRaceName);
        CreaturesModelParameter parameter = new CreaturesModelParameter(creaturesByRaceIdName);
        return new CreaturesModel(parameter);
    }

    @Override
    public void updateView() {
        if (getModel() == null) {
            setModulesComboBoxValue(null);
            return;
        }

        String moduleName = getModel().getParameter().getModuleName();
        if (isElementExistsInComboBox(moduleName)) {
            setModulesComboBoxValue(moduleName);
        } else {
            setModulesComboBoxValue(null);
        }
    }
}
