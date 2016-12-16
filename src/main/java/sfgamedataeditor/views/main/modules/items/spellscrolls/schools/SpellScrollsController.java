package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.main.modules.merchants.inventory.items.models.SpellScrollsModelCreator;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class SpellScrollsController extends AbstractModulesController<ModuleParameter, SpellScrollsListView, SpellScrollsParametersModel> {

    private SpellScrollsModelCreator modelCreator = new SpellScrollsModelCreator();

    public SpellScrollsController(SpellScrollsListView view) {
        super(view);
    }

    @Override
    protected SpellScrollsParametersModel createModel() {
        String selectedSpellScroll = getView().getSelectedModuleValue();
        int itemId = ViewTools.getKeyByPropertyValue(selectedSpellScroll, I18NTypes.ITEMS);
        return modelCreator.createModel(itemId);
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
