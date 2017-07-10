package sfgamedataeditor.views.common.model.creators;

import sfgamedataeditor.mvc.objects.Model;

public interface ModelCreator<C extends Model> {
    C createModel(int objectId);
    String getIconPath();
}
