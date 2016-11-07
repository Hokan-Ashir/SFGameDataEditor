package sfgamedataeditor.events.processing.strategies.content.modelcreators;

import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.tableservices.SpellNameTableService;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;
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
        List<SpellParameters> spellParametersBySpellSchool = SpellParametersTableService.INSTANCE.getSpellParametersBySpellSchool(spellId);
        List<String> listOfSpells = new ArrayList<>();
        for (SpellParameters spellParameters : spellParametersBySpellSchool) {
            String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParameters.spellNameId);
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
