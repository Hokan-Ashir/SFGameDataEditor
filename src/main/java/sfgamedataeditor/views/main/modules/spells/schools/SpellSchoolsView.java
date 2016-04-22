package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.ShowSpellsViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellEventParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;

import java.util.Map;
import java.util.Set;

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
        Map<String, Set<Integer>> spellSchoolsToSpellsMap = OffsetProvider.INSTANCE.getSpellSchoolsToSpellsMap();
        for (String s : spellSchoolsToSpellsMap.keySet()) {
            addMapping(s, event);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setEventParameter(ShowViewEvent event) {
        String selectedSpellSchool = (String) getSelectedModuleValue();
        parameter.setSpellSchoolName(selectedSpellSchool);
        event.setObjectParameter(parameter);
    }
}
