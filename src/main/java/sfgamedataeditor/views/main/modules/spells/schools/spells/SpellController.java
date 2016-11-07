package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.database.tableservices.SpellNameTableService;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModelParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.util.List;

public class SpellController extends AbstractModulesController<SpellModelParameter, SpellsView, SpellParameterModel> {
    public SpellController(SpellsView view) {
        super(view);
    }

    @Override
    protected SpellParameterModel createModel() {
        String selectedSpellName = getView().getSelectedModuleValue();
        Integer spellId = SpellNameTableService.INSTANCE.getSpellId(selectedSpellName);
        SpellParameterModelParameter parameter = new SpellParameterModelParameter(spellId, 1);
        return new SpellParameterModel(parameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        List<String> listOfSpells = getModel().getParameter().getListOfSpells();
        // TODO make this use-case work:
        // user selected Fire/Fireball-1, changed mana usage from 30 to 34
        // made sf-mod file and load it; cause of reloading
        // list of spells according to spellRequirement
        // all is fine, but instead of "Fireball" selected spell
        // it is first one ("Acid cloud" by default) in spell comboBox
        getView().clearComboBoxAndMapping();

        for (String spell : listOfSpells) {
            getView().addMapping(spell, SpellParameterView.class);
        }

        getView().reinitializeComboBox();
        setModulesComboBoxValue(getModel().getParameter().getSelectedSpell());
    }
}
