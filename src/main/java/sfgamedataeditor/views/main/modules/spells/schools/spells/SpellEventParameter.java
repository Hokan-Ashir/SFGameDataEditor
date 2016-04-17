package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.datamapping.SpellRequirementTuple;

public class SpellEventParameter {

    private SpellRequirementTuple spellSchoolRequirement;

    public SpellEventParameter(SpellRequirementTuple spellSchoolRequirement) {
        this.spellSchoolRequirement = spellSchoolRequirement;
    }

    public SpellRequirementTuple getSpellSchoolRequirement() {
        return spellSchoolRequirement;
    }

    public void setSpellSchoolRequirement(SpellRequirementTuple spellSchoolRequirement) {
        this.spellSchoolRequirement = spellSchoolRequirement;
    }
}
