package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersObject;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ObjectTuple;
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
        List<ObjectTuple> tuples = new ArrayList<>();
        ObjectTuple tuple = new ObjectTuple(getView().getSelectedModuleName(), getView().getSelectedModuleObjectId());
        List<SpellParametersObject> spellParameterObjects = SpellParametersTableService.INSTANCE.getSpells(tuple);
        for (SpellParametersObject spellParametersObject : spellParameterObjects) {
            String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParametersObject.spellNameId);
            if (spellName == null) {
                continue;
            }

            boolean isSpellNameExists = false;
            for (ObjectTuple objectTuple : tuples) {
                if (objectTuple.getName().equals(spellName)) {
                    isSpellNameExists = true;
                    break;
                }
            }

            if (!isSpellNameExists) {
                tuples.add(new ObjectTuple(spellName, spellParametersObject.spellNameId));
            }
        }

        SpellModelParameter spellModelParameter = new SpellModelParameter(tuples, null);
        return new SpellModel(spellModelParameter);
    }
}
