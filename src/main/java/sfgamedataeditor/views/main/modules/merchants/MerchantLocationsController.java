package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public class MerchantLocationsController extends AbstractModulesController<ModuleParameter, MerchantLocationsView, ModulesModel> {

    public MerchantLocationsController(MerchantLocationsView view) {
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
