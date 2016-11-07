package sfgamedataeditor.views.main.modules.items.spellscrolls;

import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public class SpellScrollsController extends AbstractModulesController<ModuleParameter, SpellScrollsListView, ModulesModel> {

    public SpellScrollsController(SpellScrollsListView view) {
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
