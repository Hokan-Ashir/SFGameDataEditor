package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.corpseloot.CreatureCorpseLootObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;

import java.util.List;

public class CreaturesParametersModelParameter {
    private final CreatureParameterObject creatureParameterObject;
    private final CreaturesCommonParameterObject creatureCommonParameterObject;
    private final List<CreatureEquipmentObject> creatureEquipment;
    private final List<CreatureSpellObject> creatureSpells;
    private final List<CreatureCorpseLootObject> corpseLootObjects;

    public CreaturesParametersModelParameter(CreatureParameterObject creatureParameterObject,
                                             CreaturesCommonParameterObject creatureCommonParameterObject,
                                             List<CreatureEquipmentObject> creatureEquipment,
                                             List<CreatureSpellObject> creatureSpells,
                                             List<CreatureCorpseLootObject> corpseLootObjects) {
        this.creatureParameterObject = creatureParameterObject;
        this.creatureCommonParameterObject = creatureCommonParameterObject;
        this.creatureEquipment = creatureEquipment;
        this.creatureSpells = creatureSpells;
        this.corpseLootObjects = corpseLootObjects;
    }

    public CreatureParameterObject getCreatureParameterObject() {
        return creatureParameterObject;
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

    public List<CreatureCorpseLootObject> getCorpseLootObjects() {
        return corpseLootObjects;
    }
}
