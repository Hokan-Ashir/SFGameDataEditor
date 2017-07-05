package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersObject;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ObjectTuple;
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
        List<ObjectTuple> tuples = new ArrayList<>();
        for (SpellParametersObject spellParametersObject : spellParametersBySpellSchoolObject) {
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

        String selectedSpellName = SpellNameTableService.INSTANCE.getSpellName(spellId);
        SpellModelParameter parameter = new SpellModelParameter(tuples, new ObjectTuple(selectedSpellName));
        return new SpellModel(parameter);
    }
}
