package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.corpseloot.CreatureCorpseLootObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.mvc.objects.IconableParameter;
import sfgamedataeditor.views.common.ObjectTuple;

import javax.swing.*;
import java.util.List;

public class CreaturesParametersModelParameter extends IconableParameter {
    private final CreatureParameterObject creatureParameterObject;
    private final CreaturesCommonParameterObject creatureCommonParameterObject;
    private final List<CreatureEquipmentObject> creatureEquipment;
    private final List<CreatureSpellObject> creatureSpells;
    private final List<CreatureCorpseLootObject> corpseLootObjects;
    private final List<ObjectTuple> merchantItemIds;
    private final String selectedMerchantItem;

    public CreaturesParametersModelParameter(CreatureParameterObject creatureParameterObject,
                                             CreaturesCommonParameterObject creatureCommonParameterObject,
                                             List<CreatureEquipmentObject> creatureEquipment,
                                             List<CreatureSpellObject> creatureSpells,
                                             List<CreatureCorpseLootObject> corpseLootObjects,
                                             List<ObjectTuple> merchantItemIds,
                                             String selectedMerchantItem,
                                             Icon icon) {
        super(icon);
        this.creatureParameterObject = creatureParameterObject;
        this.creatureCommonParameterObject = creatureCommonParameterObject;
        this.creatureEquipment = creatureEquipment;
        this.creatureSpells = creatureSpells;
        this.corpseLootObjects = corpseLootObjects;
        this.merchantItemIds = merchantItemIds;
        this.selectedMerchantItem = selectedMerchantItem;
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

    public List<ObjectTuple> getMerchantItemIds() {
        return merchantItemIds;
    }

    public String getSelectedMerchantItem() {
        return selectedMerchantItem;
    }
}
