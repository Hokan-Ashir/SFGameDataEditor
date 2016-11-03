package sfgamedataeditor.mvc;

import sfgamedataeditor.mvc.objects.Model;

public interface ModelCreator {
    Model createModel(Model parentViewModel);
}
