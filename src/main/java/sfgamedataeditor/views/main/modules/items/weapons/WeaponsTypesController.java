package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public class WeaponsTypesController extends AbstractModulesController<ModuleParameter, WeaponsTypesListView, ModulesModel> {

    public WeaponsTypesController(WeaponsTypesListView view) {
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
