package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public class MiscellaneousController extends AbstractModulesController<ModuleParameter, MiscellaneousListView, ModulesModel> {

    public MiscellaneousController(MiscellaneousListView view) {
        super(view);
    }

    @Override
    protected ModulesModel createModel() {
        return null;
    }

    @Override
    public void updateView() {

    }
}
