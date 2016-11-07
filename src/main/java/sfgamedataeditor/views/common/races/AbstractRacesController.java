package sfgamedataeditor.views.common.races;

import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public class AbstractRacesController extends AbstractModulesController<ModuleParameter, AbstractRacesView, ModulesModel> {

    public AbstractRacesController(AbstractRacesView view) {
        super(view);
        getView().getModulesComboBox().setEnabled(false);
    }

    @Override
    protected ModulesModel createModel() {
        return null;
    }

    @Override
    public void updateView() {

    }
}
