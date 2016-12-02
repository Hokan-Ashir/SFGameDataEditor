package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;

public class CreaturesParametersModelParameter {
    private final CreatureParameterObject creatureParameterObject;

    public CreaturesParametersModelParameter(CreatureParameterObject creatureParameterObject) {
        this.creatureParameterObject = creatureParameterObject;
    }

    public CreatureParameterObject getCreatureParameterObject() {
        return creatureParameterObject;
    }
}
