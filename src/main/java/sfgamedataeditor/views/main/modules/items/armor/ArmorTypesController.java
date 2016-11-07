package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public class ArmorTypesController extends AbstractModulesController<ModuleParameter, ArmorTypeListView, ModulesModel> {

    public ArmorTypesController(ArmorTypeListView view) {
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
