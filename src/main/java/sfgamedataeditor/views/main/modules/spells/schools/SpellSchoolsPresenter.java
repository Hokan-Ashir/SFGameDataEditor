package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersObject;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModelParameter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpellSchoolsPresenter extends AbstractModulesPresenter<ModuleParameter, SpellSchoolsView, SpellModel> {
    public SpellSchoolsPresenter(SpellSchoolsView view) {
        super(view);
    }

    @Override
    protected SpellModel createModel() {
        Set<String> listOfSpells = new HashSet<>();
        List<SpellParametersObject> spellParameterObjects = SpellParametersTableService.INSTANCE.getSpells(getView().getSelectedModuleValue());
        for (SpellParametersObject spellParametersObject : spellParameterObjects) {
            String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParametersObject.spellNameId);
            if (spellName == null) {
                continue;
            }

            listOfSpells.add(spellName);
        }

        SpellModelParameter spellModelParameter = new SpellModelParameter(listOfSpells, null);
        return new SpellModel(spellModelParameter);
    }
}
