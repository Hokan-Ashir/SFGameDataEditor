package sfgamedataeditor.views.main.modules.units.races.units;


import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentTableService;
import sfgamedataeditor.database.creatures.production.buildings.CreatureBuildingsObject;
import sfgamedataeditor.database.creatures.production.buildings.CreatureBuildingsTableService;
import sfgamedataeditor.database.creatures.production.resources.CreatureResourcesObject;
import sfgamedataeditor.database.creatures.production.resources.CreatureResourcesTableService;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellTableService;
import sfgamedataeditor.views.common.ModelCreator;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersModel;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersModelParameter;

import javax.swing.*;
import java.util.List;

public class UnitModelCreator implements ModelCreator<UnitsParametersModel> {
    @Override
    public UnitsParametersModel createModel(int objectId, Icon icon) {
        CreaturesCommonParameterObject commonParameterObject = CreatureCommonParametersTableService.INSTANCE.getCreatureParametersByCreatureId(objectId);
        List<CreatureEquipmentObject> creatureEquipment = CreatureEquipmentTableService.INSTANCE.getCreatureEquipmentByCreatureId(objectId);
        List<CreatureSpellObject> creatureSpells = CreatureSpellTableService.INSTANCE.getCreatureSpellsByCreatureId(objectId);
        List<CreatureResourcesObject> creatureResources = CreatureResourcesTableService.INSTANCE.getCreatureResourcesByCreatureId(objectId);
        List<CreatureBuildingsObject> creatureBuildings = CreatureBuildingsTableService.INSTANCE.getCreatureBuildingsByCreatureId(objectId);
        UnitsParametersModelParameter parameter = new UnitsParametersModelParameter(commonParameterObject,
                creatureEquipment, creatureSpells, creatureResources, creatureBuildings, icon);
        return new UnitsParametersModel(parameter);
    }
}
