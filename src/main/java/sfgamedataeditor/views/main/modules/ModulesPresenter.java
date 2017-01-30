package sfgamedataeditor.views.main.modules;

import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public class ModulesPresenter extends AbstractModulesPresenter<ModuleParameter, ModulesView, ModulesModel> {
    public ModulesPresenter(ModulesView view) {
        super(view);
    }

    @Override
    protected ModulesModel createModel() {
        return null;
    }

    @Override
    protected void updateSubViewsContent() {

    }
}
