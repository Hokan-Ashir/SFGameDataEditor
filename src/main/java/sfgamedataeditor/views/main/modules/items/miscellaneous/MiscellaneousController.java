package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersModel;
import sfgamedataeditor.views.main.modules.merchants.inventory.items.models.MiscellaneousModelCreator;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ResourceBundle;
import java.util.Set;

public class MiscellaneousController extends AbstractModulesController<ModuleParameter, MiscellaneousListView, MiscellaneousParametersModel> {

    private MiscellaneousModelCreator modelCreator = new MiscellaneousModelCreator();

    public MiscellaneousController(MiscellaneousListView view) {
        super(view);
    }

    @Override
    protected MiscellaneousParametersModel createModel() {
        int itemId = getItemId();
        return modelCreator.createModel(itemId);
    }

    private int getItemId() {
        String selectedWeaponPiece = getView().getSelectedModuleValue();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEMS);
        int itemId = 0;
        Set<String> keySet = bundle.keySet();
        for (String key : keySet) {
            if (bundle.getString(key).equals(selectedWeaponPiece)) {
                itemId = Integer.parseInt(key);
                break;
            }
        }
        return itemId;
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
