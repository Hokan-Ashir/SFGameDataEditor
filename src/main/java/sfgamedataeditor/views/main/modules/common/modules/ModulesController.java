package sfgamedataeditor.views.main.modules.common.modules;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.ModulesModel;

public class ModulesController extends AbstractController<ModulesModel, ModulesView> {
    public ModulesController(ModulesView view) {
        super(view);
    }

    @Override
    public void updateView() {
        if (getModel() == null) {
            getView().setModulesComboBoxValue(null);
            return;
        }

        String moduleName = getModel().getParameter().getModuleName();
        getView().setModulesComboBoxValue(moduleName);
    }
}
