package sfgamedataeditor.views.main.modules;

import sfgamedataeditor.mvc.objects.Model;

import javax.swing.*;

public interface ModelCreator<C extends Model> {
    C createModel(int itemId, Icon icon);
}
