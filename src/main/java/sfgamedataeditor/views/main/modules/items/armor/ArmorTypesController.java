package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.armor.parameters.ArmorParametersModel;
import sfgamedataeditor.views.main.modules.items.armor.parameters.ArmorParametersModelParameter;

public class ArmorTypesController extends AbstractModulesController<ModuleParameter, ArmorTypeListView, ArmorParametersModel> {

    public ArmorTypesController(ArmorTypeListView view) {
        super(view);
    }

    @Override
    protected ArmorParametersModel createModel() {
        String moduleName = getModel().getParameter().getModuleName();

        ArmorParametersModelParameter parameter = new ArmorParametersModelParameter();
        return new ArmorParametersModel(parameter);
    }

    @Override
    public void updateView() {
        if (getModel() == null) {
            setModulesComboBoxValue(null);
            return;
        }

        String moduleName = getModel().getParameter().getModuleName();
        if (isElementExistsInComboBox(moduleName)) {
            setModulesComboBoxValue(moduleName);
        } else {
            setModulesComboBoxValue(null);
        }
    }
}
