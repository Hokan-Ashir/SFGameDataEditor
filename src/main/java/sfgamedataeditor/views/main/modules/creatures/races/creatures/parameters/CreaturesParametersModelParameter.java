package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;

import java.util.List;

public class CreaturesParametersModelParameter {
    private final CreatureParameterObject creatureParameterObject;
    private final List<CreatureEquipmentObject> creatureEquipment;

    public CreaturesParametersModelParameter(CreatureParameterObject creatureParameterObject, List<CreatureEquipmentObject> creatureEquipment) {
        this.creatureParameterObject = creatureParameterObject;
        this.creatureEquipment = creatureEquipment;
    }

    public CreatureParameterObject getCreatureParameterObject() {
        return creatureParameterObject;
    }

    public List<CreatureEquipmentObject> getCreatureEquipment() {
        return creatureEquipment;
    }
}
