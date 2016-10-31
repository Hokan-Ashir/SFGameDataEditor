package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.tableservices.SpellNameTableService;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.util.List;

public class SpellController extends AbstractController<SpellModel, SpellsView> {
    public SpellController(SpellsView view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        // TODO make this use-case work:
        // user selected Fire/Fireball-1, changed mana usage from 30 to 34
        // made sf-mod file and load it; cause of reloading
        // list of spells according to spellRequirement
        // all is fine, but instead of "Fireball" selected spell
        // it is first one ("Acid cloud" by default) in spell comboBox

        getView().clearComboBoxAndMapping();

        String spellSchoolName = getModel().getParameter().getParameter().getSpellSchoolName();
        // TODO optimize Database requests
        List<SpellParameters> spells = SpellParametersTableService.INSTANCE.getSpells(spellSchoolName);
        for (SpellParameters spellParameters : spells) {
            String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParameters.spellNameId);
            if (spellName != null) {
                getView().addMapping(spellName, SpellParameterView.class);
            }
        }

        getView().reinitializeComboBox();
    }
}
