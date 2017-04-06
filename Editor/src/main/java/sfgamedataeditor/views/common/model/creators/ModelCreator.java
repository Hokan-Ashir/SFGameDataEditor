package sfgamedataeditor.views.common.model.creators;

import sfgamedataeditor.mvc.objects.Model;

import javax.swing.*;

public interface ModelCreator<C extends Model> {
    C createModel(int objectId, Icon icon);
}
