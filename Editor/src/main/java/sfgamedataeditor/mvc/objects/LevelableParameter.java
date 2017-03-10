package sfgamedataeditor.mvc.objects;

import javax.swing.*;

public class LevelableParameter extends IconableParameter {
    private Integer level;

    protected LevelableParameter(Integer level, Icon icon) {
        super(icon);
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
