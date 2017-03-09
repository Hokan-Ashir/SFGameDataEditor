package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.mvc.objects.LevelableParameter;

import javax.swing.*;

public class SpellScrollsParametersModelParameter extends LevelableParameter {
    private final String scrollBaseName;
    private final Icon icon;

    public SpellScrollsParametersModelParameter(String scrollBaseName, int scrollLevel, Icon icon) {
        super(scrollLevel);
        this.scrollBaseName = scrollBaseName;
        this.icon = icon;
    }

    public String getScrollBaseName() {
        return scrollBaseName;
    }

    public Icon getIcon() {
        return icon;
    }
}
