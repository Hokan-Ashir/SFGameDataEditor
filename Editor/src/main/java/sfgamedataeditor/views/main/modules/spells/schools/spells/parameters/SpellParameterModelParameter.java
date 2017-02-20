package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import javax.swing.*;

public class SpellParameterModelParameter {

    private final int spellId;
    private int spellLevel;
    private final Icon icon;

    public SpellParameterModelParameter(int spellId, int spellLevel, Icon icon) {
        this.spellId = spellId;
        this.spellLevel = spellLevel;
        this.icon = icon;
    }

    public int getSpellId() {
        return spellId;
    }

    public int getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(int spellLevel) {
        this.spellLevel = spellLevel;
    }

    public Icon getIcon() {
        return icon;
    }
}
