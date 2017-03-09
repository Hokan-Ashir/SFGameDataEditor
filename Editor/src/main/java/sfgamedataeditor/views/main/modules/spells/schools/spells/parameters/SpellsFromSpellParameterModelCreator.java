package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersObject;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModelParameter;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SpellsFromSpellParameterModelCreator implements ModelCreator<SpellModel, SpellParameterModel> {
    @Override
    public SpellModel createModel(SpellParameterModel childModel) {
        int spellId = childModel.getParameter().getSpellId();
        int spellLevel = childModel.getParameter().getSpellLevel();
        List<SpellParametersObject> spellParametersBySpellSchoolObject = SpellParametersTableService.INSTANCE.getSpellParametersBySpellSchool(spellId, spellLevel);
        Set<String> listOfSpells = new TreeSet<>();
        for (SpellParametersObject spellParametersObject : spellParametersBySpellSchoolObject) {
            String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParametersObject.spellNameId);
            if (spellName == null) {
                continue;
            }

            listOfSpells.add(spellName);
        }

        String selectedSpellName = SpellNameTableService.INSTANCE.getSpellName(spellId);
        SpellModelParameter parameter = new SpellModelParameter(listOfSpells, selectedSpellName);
        return new SpellModel(parameter);
    }
}
