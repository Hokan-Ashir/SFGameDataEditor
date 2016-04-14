package sfgamedataeditor.views.spells;

import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.dataextraction.SpellMap;
import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.datamapping.SpellRequirementTuple;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;

import java.util.Map;
import java.util.Set;

public class SpellsView extends AbstractModulesView<SpellSchoolsView> {

    public SpellsView(SpellSchoolsView parentView) {
        super(parentView);
        setModulesLabelText(I18N.getMessage("spells"));
    }

    @Override
    protected void fillComboBoxMapping() {
    }

    @Override
    public void show() {
        clearComboBoxAndMapping();
        getParentView().getSubModulesPanel().removeAll();

        String selectedSpellSchool = (String) getParentView().getSelectedModuleValue();
        SpellRequirementTuple schoolRequirement = Mappings.INSTANCE.SPELL_SCHOOL_MAP.get(selectedSpellSchool);
        Map<SpellRequirementTuple, Set<Integer>> requirementToSpellMap = OffsetProvider.INSTANCE.getRequirementToSpellMap();
        Set<Integer> spellIds = requirementToSpellMap.get(schoolRequirement);
        if (spellIds != null && !spellIds.isEmpty()) {
            for (Integer spellId : spellIds) {
                if (!OffsetProvider.INSTANCE.getSpellOffsets().containsKey(spellId)) {
                    continue;
                }

                String spellName = SpellMap.INSTANCE.getSpellMap().get(spellId).getKey();
                getComboBoxMapping().put(spellName, SpellParameterView.class);
            }

            reinitializeComboBox();
            getParentView().getSubModulesPanel().add(getMainPanel());
        }

        getParentView().getSubModulesPanel().revalidate();
        getParentView().getSubModulesPanel().repaint();
    }
}
