package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

public class SpellParameterEventParameter {

    private Integer spellId;
    private Integer spellLevel;

    public SpellParameterEventParameter() {
    }

    public SpellParameterEventParameter(Integer spellId, Integer spellLevel) {
        this.spellId = spellId;
        this.spellLevel = spellLevel;
    }

    public Integer getSpellId() {
        return spellId;
    }

    public void setSpellId(Integer spellId) {
        this.spellId = spellId;
    }

    public Integer getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(Integer spellLevel) {
        this.spellLevel = spellLevel;
    }
}
