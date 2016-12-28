package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentTableService;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModelParameter;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class CreaturesController extends AbstractModulesController<CreaturesModelParameter, CreaturesView, CreaturesParametersModel> {

    public CreaturesController(CreaturesView view) {
        super(view);
    }

    @Override
    protected CreaturesParametersModel createModel() {
        String selectedCreatureName = getView().getSelectedModuleValue();
        CreatureParameterObject creatureParameterObject = CreatureParametersTableService.INSTANCE.getCreatureParameterObjectByCreatureName(selectedCreatureName);
        Integer creatureId = ViewTools.getKeyByPropertyValue(selectedCreatureName, I18NTypes.CREATURES);
        List<CreatureEquipmentObject> creatureEquipment = CreatureEquipmentTableService.INSTANCE.getCreatureEquipmentByCreatureId(creatureId);
        CreaturesParametersModelParameter parameter = new CreaturesParametersModelParameter(creatureParameterObject, creatureEquipment);
        return new CreaturesParametersModel(parameter);
    }

    @Override
    public void updateView() {
        List<String> creatureParameterObjectList = getModel().getParameter().getCreatureNames();
        getView().clearComboBoxAndMapping();

        for (String creatureName : creatureParameterObjectList) {
            getView().addMapping(creatureName, CreaturesParametersView.class);
        }

        getView().reinitializeComboBox();
        setModulesComboBoxValue(getModel().getParameter().getSelectedCreatureName());
    }
}
