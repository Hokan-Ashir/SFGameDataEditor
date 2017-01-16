package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModelParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SpellPresenter extends AbstractModulesPresenter<SpellModelParameter, SpellsView, SpellParameterModel> {
    public SpellPresenter(SpellsView view) {
        super(view);
    }

    @Override
    protected SpellParameterModel createModel() {
        String selectedSpellName = getView().getSelectedModuleValue();
        Integer spellId = SpellNameTableService.INSTANCE.getSpellId(selectedSpellName);
        Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(spellId);

        int spellMinLevel = (int) ((TreeSet) spellLevels).first();
        int spellMaxLevel = (int) ((TreeSet) spellLevels).last();
        int selectedLevel = adjustSelectedLevel(1, spellMinLevel, spellMaxLevel);
        SpellParameterModelParameter parameter = new SpellParameterModelParameter(spellId, selectedLevel);
        return new SpellParameterModel(parameter);
    }

    // in case user selected spell with level-range [1; 12] with level 5
    // then selected spell with level range [13; 20]
    // we have to select level 13 to appropriately load correct data
    // and vice versa, if user selected spell with level range [13; 20]
    // and then selected spell with level range [1; 12]
    private int adjustSelectedLevel(int selectedLevel, int spellMinLevel, int spellMaxLevel) {
        if (selectedLevel <= spellMaxLevel && selectedLevel >= spellMinLevel) {
            return selectedLevel;
        } else {
            return spellMinLevel;
        }
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
