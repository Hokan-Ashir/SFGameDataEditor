package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.tableservices.SpellNameTableService;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModelParameter;

import java.util.ArrayList;
import java.util.List;

public class SpellSchoolsController extends AbstractModulesController<ModuleParameter, SpellSchoolsView, SpellModel> {
    public SpellSchoolsController(SpellSchoolsView view) {
        super(view);
    }

    @Override
    protected SpellModel createModel() {
        List<String> listOfSpells = new ArrayList<>();
        List<SpellParameters> spellParameterses = SpellParametersTableService.INSTANCE.getSpells(getView().getSelectedModuleValue());
        for (SpellParameters spellParameters : spellParameterses) {
            String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParameters.spellNameId);
            if (spellName == null) {
                continue;
            }

            listOfSpells.add(spellName);
        }

        SpellModelParameter spellModelParameter = new SpellModelParameter(listOfSpells, null);
        return new SpellModel(spellModelParameter);
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
