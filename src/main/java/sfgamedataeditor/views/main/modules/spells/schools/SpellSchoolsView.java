package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.database.objects.SpellSchoolName;
import sfgamedataeditor.database.tableservices.SpellSchoolNameTableService;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.ShowSpellsViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellEventParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellViewMetaEvent;

import java.util.List;

public class SpellSchoolsView extends AbstractModulesView<MainView, SpellSchoolsMetaEvent> {

    private SpellEventParameter parameter = new SpellEventParameter(null);

    public SpellSchoolsView(MainView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("spellSchools"));
        EventHandlerRegister.INSTANCE.addEventHandler(new SpellEventHandler());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        List<SpellSchoolName> allSpellSchoolNames = SpellSchoolNameTableService.INSTANCE.getAllSpellSchoolNames();
        for (SpellSchoolName allSpellSchoolName : allSpellSchoolNames) {
            addMapping(allSpellSchoolName.name, new SpellViewMetaEvent());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setEventParameter(AbstractMetaEvent metaEvent) {
        super.setEventParameter(metaEvent);
        String selectedSpellSchool = getSelectedModuleValue();
        parameter.setSpellSchoolName(selectedSpellSchool);
        metaEvent.setEventParameter(ShowSpellsViewEvent.class, parameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<SpellSchoolsMetaEvent> getMetaEventClass() {
        return SpellSchoolsMetaEvent.class;
    }
}
