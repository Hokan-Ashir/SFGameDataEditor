package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;

public class ItemTypesPresenter extends AbstractModulesPresenter<ModuleParameter, ItemTypesView, ModulesModel> {

    public ItemTypesPresenter(ItemTypesView view) {
        super(view);
    }

    @Override
    protected ModulesModel createModel() {
        return null;
    }
}
