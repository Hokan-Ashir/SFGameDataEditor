package sfgamedataeditor.mvc;

import sfgamedataeditor.mvc.objects.Model;

public interface ModelCreator<P extends Model, C extends Model> {
    P createModel(C childModel);
}
