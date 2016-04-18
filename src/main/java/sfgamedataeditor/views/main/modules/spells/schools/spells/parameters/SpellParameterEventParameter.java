package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

public class SpellParameterEventParameter {

    private int spellId;
    private int spellLevel;

    public SpellParameterEventParameter() {
    }

    public SpellParameterEventParameter(int spellId, int spellLevel) {
        this.spellId = spellId;
        this.spellLevel = spellLevel;
    }

    public int getSpellId() {
        return spellId;
    }

    public void setSpellId(int spellId) {
        this.spellId = spellId;
    }

    public int getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(int spellLevel) {
        this.spellLevel = spellLevel;
    }
}
