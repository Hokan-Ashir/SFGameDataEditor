package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersObject;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModelParameter;

import java.util.ArrayList;
import java.util.List;

public class SpellsFromSpellParameterModelCreator implements ModelCreator<SpellModel, SpellParameterModel> {
    @Override
    public SpellModel createModel(SpellParameterModel childModel) {
        int spellId = childModel.getParameter().getSpellId();
        int spellLevel = childModel.getParameter().getLevel();
        List<SpellParametersObject> spellParametersBySpellSchoolObject = SpellParametersTableService.INSTANCE.getSpellParametersBySpellSchool(spellId, spellLevel);
        List<SubViewPanelTuple> tuples = new ArrayList<>();
        for (SpellParametersObject spellParametersObject : spellParametersBySpellSchoolObject) {
            String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParametersObject.spellNameId);
            if (spellName == null) {
                continue;
            }

            tuples.add(new SubViewPanelTuple(spellName, spellParametersObject.spellNumber));
        }

        String selectedSpellName = SpellNameTableService.INSTANCE.getSpellName(spellId);
        SpellModelParameter parameter = new SpellModelParameter(tuples, selectedSpellName);
        return new SpellModel(parameter);
    }
}
