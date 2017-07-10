package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModelParameter;

import javax.swing.*;
import java.util.Set;
import java.util.TreeSet;

public class SpellPresenter extends AbstractModulesPresenter<SpellModelParameter, SpellsView, SpellParameterModel> {

    public SpellPresenter(SpellsView view) {
        super(view);
    }

    @Override
    protected SpellParameterModel createModel() {
        String selectedSpellName = getView().getSelectedModuleName();
        Integer spellId = SpellNameTableService.INSTANCE.getSpellId(selectedSpellName);
        Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(spellId);

        int spellMinLevel = (int) ((TreeSet) spellLevels).first();
        int spellMaxLevel = (int) ((TreeSet) spellLevels).last();
        int selectedLevel = adjustSelectedLevel(1, spellMinLevel, spellMaxLevel);
        Icon icon = ImageIconsCache.INSTANCE.getImageIcon("/images/spells_and_scrolls/", spellId);
        SpellParameterModelParameter parameter = new SpellParameterModelParameter(spellId, selectedLevel, icon);
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
}
