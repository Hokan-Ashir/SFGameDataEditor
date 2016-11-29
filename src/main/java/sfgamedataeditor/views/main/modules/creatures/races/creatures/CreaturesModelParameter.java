package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.database.creatures.CreatureParameterObject;

import java.util.List;

public class CreaturesModelParameter {
    private final List<CreatureParameterObject> creatureParameterObjectList;

    public CreaturesModelParameter(List<CreatureParameterObject> creatureParameterObjectList) {
        this.creatureParameterObjectList = creatureParameterObjectList;
    }

    public List<CreatureParameterObject> getCreatureParameterObjectList() {
        return creatureParameterObjectList;
    }
}
