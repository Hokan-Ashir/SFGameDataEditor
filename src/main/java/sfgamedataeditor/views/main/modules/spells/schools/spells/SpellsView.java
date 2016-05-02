package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.dataextraction.SpellMap;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.ShowSpellParameterViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterEventParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterViewMetaEvent;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpellsView extends AbstractModulesView<SpellSchoolsView> {

    private final SpellParameterEventParameter spellParameterEventParameter;
    private final SpellParameterViewMetaEvent event;
    private SpellEventParameter spellEventParameter;

    public SpellsView(SpellSchoolsView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("spells"));
        spellParameterEventParameter = new SpellParameterEventParameter();
        spellEventParameter = new SpellEventParameter();
        event = new SpellParameterViewMetaEvent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateData(Object data) {
        if (data != null) {
            // TODO get rid of class casting
            spellEventParameter = (SpellEventParameter) data;
        }
        // TODO make this use-case work:
        // user selected Fire/Fireball-1, changed mana usage from 30 to 34
        // made sf-mod file and load it; cause of reloading
        // list of spells according to spellRequirement
        // all is fine, but instead of "Fireball" selected spell
        // it is first one ("Acid cloud" by default) in spell comboBox

        clearComboBoxAndMapping();

        String spellSchoolName = spellEventParameter.getSpellSchoolName();
        Map<String, Set<Integer>> spellSchoolsToSpellsMap = OffsetProvider.INSTANCE.getSpellSchoolsToSpellsMap();
        Set<Integer> spellIds = spellSchoolsToSpellsMap.get(spellSchoolName);
        if (spellIds == null || spellIds.isEmpty()) {
            return;
        }

        for (Integer spellId : spellIds) {
            if (!OffsetProvider.INSTANCE.getSpellOffsets().containsKey(spellId)) {
                continue;
            }

            String spellName = SpellMap.INSTANCE.getSpellMap().get(spellId).getKey();
            addMapping(spellName, event);
        }

        reinitializeComboBox();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setEventParameter(AbstractMetaEvent metaEvent) {
        spellParameterEventParameter.setSpellId(getSelectedSpellId());

        LevelableView<SpellsView> levelableView = (LevelableView<SpellsView>) ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, SpellsView.class));
        if (levelableView != null) {
            spellParameterEventParameter.setSpellLevel(levelableView.getSelectedLevel());
        }

        metaEvent.setEventParameter(ShowSpellParameterViewEvent.class, spellParameterEventParameter);
    }

    private int getSelectedSpellId() {
        String selectedSpellName = (String) getSelectedModuleValue();
        int selectedSpellId = 0;
        for (Map.Entry<Integer, Pair<String, List<String>>> integerPairEntry : SpellMap.INSTANCE.getSpellMap().entrySet()) {
            String spellName = integerPairEntry.getValue().getKey();
            if (selectedSpellName.equals(spellName)) {
                selectedSpellId = integerPairEntry.getKey();
                break;
            }
        }
        return selectedSpellId;
    }
}
