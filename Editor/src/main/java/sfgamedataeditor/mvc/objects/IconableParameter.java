package sfgamedataeditor.mvc.objects;

import javax.swing.*;

public class IconableParameter {
    private final Icon icon;

    protected IconableParameter(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }
}
