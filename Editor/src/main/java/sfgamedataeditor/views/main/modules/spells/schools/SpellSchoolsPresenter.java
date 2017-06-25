package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersObject;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModelParameter;

import java.util.ArrayList;
import java.util.List;

public class SpellSchoolsPresenter extends AbstractModulesPresenter<ModuleParameter, SpellSchoolsView, SpellModel> {
    public SpellSchoolsPresenter(SpellSchoolsView view) {
        super(view);
    }

    @Override
    protected SpellModel createModel() {
        List<SubViewPanelTuple> tuples = new ArrayList<>();
        List<SpellParametersObject> spellParameterObjects = SpellParametersTableService.INSTANCE.getSpells(getView().getSelectedModuleName());
        for (SpellParametersObject spellParametersObject : spellParameterObjects) {
            String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParametersObject.spellNameId);
            if (spellName == null) {
                continue;
            }

            tuples.add(new SubViewPanelTuple(spellName, spellParametersObject.spellNumber));
        }

        SpellModelParameter spellModelParameter = new SpellModelParameter(tuples, null);
        return new SpellModel(spellModelParameter);
    }
}
