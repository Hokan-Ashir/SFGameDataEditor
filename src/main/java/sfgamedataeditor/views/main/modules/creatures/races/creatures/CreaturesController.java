package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.database.creatures.CreatureParameterObject;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModelParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.util.List;

public class CreaturesController extends AbstractModulesController<CreaturesModelParameter, CreaturesView, CreaturesParametersModel> {

    public CreaturesController(CreaturesView view) {
        super(view);
    }

    @Override
    protected CreaturesParametersModel createModel() {
        CreaturesParametersModelParameter parameter = new CreaturesParametersModelParameter(null);
        return new CreaturesParametersModel(parameter);
    }

    @Override
    public void updateView() {
        List<CreatureParameterObject> creatureParameterObjectList = getModel().getParameter().getCreatureParameterObjectList();
        getView().clearComboBoxAndMapping();

        for (CreatureParameterObject creature : creatureParameterObjectList) {
            getView().addMapping(creature.name, SpellParameterView.class);
        }

        getView().reinitializeComboBox();
    }
}
