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
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModelParameter;

import javax.swing.*;
import java.util.List;

public class CreaturesModelCreator implements ModelCreator<CreaturesParametersModel> {
    @Override
    public CreaturesParametersModel createModel(int objectId, Icon icon) {
        CreaturesCommonParameterObject commonParameterObject = CreatureCommonParametersTableService.INSTANCE.getCreatureParametersByCreatureId(objectId);
        CreatureParameterObject creatureParameterObject = CreatureParametersTableService.INSTANCE.getCreatureParameterObjectByCreatureName(commonParameterObject.name);
        List<CreatureEquipmentObject> creatureEquipment = CreatureEquipmentTableService.INSTANCE.getCreatureEquipmentByCreatureId(objectId);
        List<CreatureSpellObject> creatureSpells = CreatureSpellTableService.INSTANCE.getCreatureSpellsByCreatureId(objectId);
        List<CreatureCorpseLootObject> corpseLootObjects = CreatureCorpseLootTableService.INSTANCE.getCreatureCorpseLootByCreatureId(objectId);
        CreaturesParametersModelParameter parameter = new CreaturesParametersModelParameter(creatureParameterObject, commonParameterObject,
                creatureEquipment, creatureSpells, corpseLootObjects, icon);
        return new CreaturesParametersModel(parameter);
    }
}
