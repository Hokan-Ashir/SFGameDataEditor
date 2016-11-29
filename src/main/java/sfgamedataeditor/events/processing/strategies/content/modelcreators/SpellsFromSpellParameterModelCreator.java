package sfgamedataeditor.events.processing.strategies.content.modelcreators;

import sfgamedataeditor.database.spellname.SpellNameTableService;
import sfgamedataeditor.database.spellparameters.SpellParametersObject;
import sfgamedataeditor.database.spellparameters.SpellParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellModelParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;

import java.util.ArrayList;
import java.util.List;

public class SpellsFromSpellParameterModelCreator implements ModelCreator<SpellModel, SpellParameterModel> {
    @Override
    public SpellModel createModel(SpellParameterModel childModel) {
        int spellId = childModel.getParameter().getSpellId();
        int spellLevel = childModel.getParameter().getSpellLevel();
        List<SpellParametersObject> spellParametersBySpellSchoolObject = SpellParametersTableService.INSTANCE.getSpellParametersBySpellSchool(spellId, spellLevel);
        List<String> listOfSpells = new ArrayList<>();
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
