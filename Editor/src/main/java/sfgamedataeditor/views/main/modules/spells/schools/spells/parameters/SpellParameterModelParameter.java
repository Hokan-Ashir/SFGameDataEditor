package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.mvc.objects.LevelableParameter;

import javax.swing.*;

public class SpellParameterModelParameter extends LevelableParameter {

    private final int spellId;

    public SpellParameterModelParameter(int spellId, int spellLevel, Icon icon) {
        super(spellLevel, icon);
        this.spellId = spellId;
    }

    public int getSpellId() {
        return spellId;
    }
}
