package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.dataextraction.SpellMap;
import sfgamedataeditor.datamapping.SpellRequirementTuple;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.LevelableView;
import sfgamedataeditor.views.common.ShowLevelableViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.ShowSpellParameterViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterEventParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpellsView extends AbstractModulesView<SpellSchoolsView> {

    private final SpellParameterEventParameter parameter;
    private List<ShowViewEvent> subViewEvents;

    public SpellsView(SpellSchoolsView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("spells"));
        parameter = new SpellParameterEventParameter();

        initializeSubViewEventList();
    }

    private void initializeSubViewEventList() {
        ClassTuple tuple1 = new ClassTuple(LevelableView.class, this);
        ShowLevelableViewEvent levelableViewEvent = new ShowLevelableViewEvent(tuple1);

        ClassTuple tuple = new ClassTuple<>(SpellParameterView.class, this);
        ShowSpellParameterViewEvent spellParameterViewEvent = new ShowSpellParameterViewEvent(tuple);

        subViewEvents = new ArrayList<>();
        subViewEvents.add(levelableViewEvent);
        subViewEvents.add(spellParameterViewEvent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
    }

    @Override
    public void updateData(Object data) {
        SpellEventParameter parameter = (SpellEventParameter) data;
        clearComboBoxAndMapping();

        SpellRequirementTuple schoolRequirement = parameter.getSpellSchoolRequirement();
        Map<SpellRequirementTuple, Set<Integer>> requirementToSpellMap = OffsetProvider.INSTANCE.getRequirementToSpellMap();
        Set<Integer> spellIds = requirementToSpellMap.get(schoolRequirement);
        if (spellIds != null && !spellIds.isEmpty()) {
            for (Integer spellId : spellIds) {
                if (!OffsetProvider.INSTANCE.getSpellOffsets().containsKey(spellId)) {
                    continue;
                }

                String spellName = SpellMap.INSTANCE.getSpellMap().get(spellId).getKey();
                addMapping(spellName, subViewEvents);
            }

            reinitializeComboBox();
        }
    }

    @Override
    protected void setEventParameter(ShowViewEvent event) {
        // TODO get rid of instance of calls
        if (event instanceof ShowSpellParameterViewEvent) {
            parameter.setSpellId(getSelectedSpellId());

            LevelableView<SpellsView> levelableView = (LevelableView<SpellsView>) ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, this));
            if (levelableView != null) {
                parameter.setSpellLevel(levelableView.getSelectedLevel());
            }

            event.setObjectParameter(parameter);
        }
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
