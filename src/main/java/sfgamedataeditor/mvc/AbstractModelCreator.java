package sfgamedataeditor.mvc;

import sfgamedataeditor.mvc.objects.Model;

public abstract class AbstractModelCreator {
    public abstract Model createModel(Model parentViewModel);
}
