package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.datamapping.SpellRequirementTuple;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.ModulesView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.ShowSpellsViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellEventParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;

public class SpellSchoolsView extends AbstractModulesView<ModulesView> {

    private SpellEventParameter parameter = new SpellEventParameter(null);

    public SpellSchoolsView(ModulesView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("spellSchools"));
        EventHandlerRegister.INSTANCE.addEventHandler(new SpellEventHandler());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        ClassTuple tuple = new ClassTuple<>(SpellsView.class, this);
        ShowSpellsViewEvent event = new ShowSpellsViewEvent(tuple);
        for (String s : Mappings.INSTANCE.SPELL_SCHOOL_MAP.keySet()) {
            addMapping(s, event);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setEventParameter(ShowViewEvent event) {
        parameter.setSpellSchoolRequirement(getSpellRequirement());
        event.setObjectParameter(parameter);
    }

    private SpellRequirementTuple getSpellRequirement() {
        String selectedSpellSchool = (String) getSelectedModuleValue();
        return Mappings.INSTANCE.SPELL_SCHOOL_MAP.get(selectedSpellSchool);
    }
}
