package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.mvc.objects.LevelableParameter;

import javax.swing.*;

public class SpellParameterModelParameter extends LevelableParameter {

    private final Integer spellId;

    public SpellParameterModelParameter(Integer spellId, Integer spellLevel, Icon icon) {
        super(spellLevel, icon);
        this.spellId = spellId;
    }

    public Integer getSpellId() {
        return spellId;
    }
}
