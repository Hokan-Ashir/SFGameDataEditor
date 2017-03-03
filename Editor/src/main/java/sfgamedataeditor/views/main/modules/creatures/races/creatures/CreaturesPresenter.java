package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.corpseloot.CreatureCorpseLootObject;
import sfgamedataeditor.database.creatures.corpseloot.CreatureCorpseLootTableService;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentTableService;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModelParameter;

import java.util.List;

public class CreaturesPresenter extends AbstractModulesPresenter<CreaturesModelParameter, CreaturesView, CreaturesParametersModel> {

    public CreaturesPresenter(CreaturesView view) {
        super(view);
    }

    @Override
    protected CreaturesParametersModel createModel() {
        String selectedCreatureName = getView().getSelectedModuleName();
        CreatureParameterObject creatureParameterObject = CreatureParametersTableService.INSTANCE.getCreatureParameterObjectByCreatureName(selectedCreatureName);
        Integer creatureId = CreatureCommonParametersTableService.INSTANCE.getCreatureIdByName(selectedCreatureName);
        CreaturesCommonParameterObject commonParameterObject = CreatureCommonParametersTableService.INSTANCE.getCreatureParametersByCreatureId(creatureId);
        List<CreatureEquipmentObject> creatureEquipment = CreatureEquipmentTableService.INSTANCE.getCreatureEquipmentByCreatureId(creatureId);
        List<CreatureSpellObject> creatureSpells = CreatureSpellTableService.INSTANCE.getCreatureSpellsByCreatureId(creatureId);
        List<CreatureCorpseLootObject> corpseLootObjects = CreatureCorpseLootTableService.INSTANCE.getCreatureCorpseLootByCreatureId(creatureId);
        CreaturesParametersModelParameter parameter = new CreaturesParametersModelParameter(creatureParameterObject, commonParameterObject,
                creatureEquipment, creatureSpells, corpseLootObjects);
        return new CreaturesParametersModel(parameter);
    }
}
