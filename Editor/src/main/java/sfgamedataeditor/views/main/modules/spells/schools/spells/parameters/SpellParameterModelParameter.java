package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.mvc.objects.LevelableParameter;

import javax.swing.*;

public class SpellParameterModelParameter extends LevelableParameter {

    private final int spellId;
    private final Icon icon;

    public SpellParameterModelParameter(int spellId, int spellLevel, Icon icon) {
        super(spellLevel);
        this.spellId = spellId;
        this.icon = icon;
    }

    public int getSpellId() {
        return spellId;
    }

    public Icon getIcon() {
        return icon;
    }
}
