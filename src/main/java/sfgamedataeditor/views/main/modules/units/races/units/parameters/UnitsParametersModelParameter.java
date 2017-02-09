package sfgamedataeditor.views.main.modules.units.races.units.parameters;

import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.production.buildings.CreatureBuildingsObject;
import sfgamedataeditor.database.creatures.production.resources.CreatureResourcesObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;

import javax.swing.*;
import java.util.List;

public class UnitsParametersModelParameter {
    private final CreaturesCommonParameterObject creatureCommonParameterObject;
    private final List<CreatureEquipmentObject> creatureEquipment;
    private final List<CreatureSpellObject> creatureSpells;
    private final List<CreatureResourcesObject> creatureResources;
    private final List<CreatureBuildingsObject> creatureBuildings;
    private final Icon icon;

    public UnitsParametersModelParameter(CreaturesCommonParameterObject creatureCommonParameterObject,
                                         List<CreatureEquipmentObject> creatureEquipment,
                                         List<CreatureSpellObject> creatureSpells,
                                         List<CreatureResourcesObject> creatureResources,
                                         List<CreatureBuildingsObject> creatureBuildings,
                                         Icon icon) {
        this.creatureCommonParameterObject = creatureCommonParameterObject;
        this.creatureEquipment = creatureEquipment;
        this.creatureSpells = creatureSpells;
        this.creatureResources = creatureResources;
        this.creatureBuildings = creatureBuildings;
        this.icon = icon;
    }

    public CreaturesCommonParameterObject getCreatureCommonParameterObject() {
        return creatureCommonParameterObject;
    }

    public List<CreatureEquipmentObject> getCreatureEquipment() {
        return creatureEquipment;
    }

    public List<CreatureSpellObject> getCreatureSpells() {
        return creatureSpells;
    }

    public List<CreatureResourcesObject> getCreatureResources() {
        return creatureResources;
    }

    public List<CreatureBuildingsObject> getCreatureBuildings() {
        return creatureBuildings;
    }

    public Icon getIcon() {
        return icon;
    }
}
