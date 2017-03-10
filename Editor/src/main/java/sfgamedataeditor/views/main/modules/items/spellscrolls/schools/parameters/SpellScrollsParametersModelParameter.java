package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.mvc.objects.LevelableParameter;

import javax.swing.*;

public class SpellScrollsParametersModelParameter extends LevelableParameter {
    private final String scrollBaseName;

    public SpellScrollsParametersModelParameter(String scrollBaseName, int scrollLevel, Icon icon) {
        super(scrollLevel, icon);
        this.scrollBaseName = scrollBaseName;
    }

    public String getScrollBaseName() {
        return scrollBaseName;
    }
}
