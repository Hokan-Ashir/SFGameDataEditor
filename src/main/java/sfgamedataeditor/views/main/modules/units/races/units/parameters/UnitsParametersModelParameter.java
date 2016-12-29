package sfgamedataeditor.views.main.modules.units.races.units.parameters;

import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;

import java.util.List;

public class UnitsParametersModelParameter {
    private final CreaturesCommonParameterObject creatureCommonParameterObject;
    private final List<CreatureEquipmentObject> creatureEquipment;
    private final List<CreatureSpellObject> creatureSpells;

    public UnitsParametersModelParameter(CreaturesCommonParameterObject creatureCommonParameterObject,
                                         List<CreatureEquipmentObject> creatureEquipment,
                                         List<CreatureSpellObject> creatureSpells) {
        this.creatureCommonParameterObject = creatureCommonParameterObject;
        this.creatureEquipment = creatureEquipment;
        this.creatureSpells = creatureSpells;
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
}
