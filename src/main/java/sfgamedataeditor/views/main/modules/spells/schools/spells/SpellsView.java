package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.tableservices.SpellNameTableService;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.ShowSpellParameterViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterEventParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterViewMetaEvent;

import java.util.List;

public class SpellsView extends AbstractModulesView<MainView, SpellViewMetaEvent> {

    private final SpellParameterEventParameter spellParameterEventParameter;
    private SpellEventParameter spellEventParameter;

    public SpellsView(MainView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("spells"));
        spellParameterEventParameter = new SpellParameterEventParameter();
        spellEventParameter = new SpellEventParameter();
//        getSubModulesPanel().setLayout(new BoxLayout(getSubModulesPanel(), BoxLayout.Y_AXIS));
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
        // TODO optimize Database requests
        List<SpellParameters> spells = SpellParametersTableService.INSTANCE.getSpells(spellSchoolName);
        for (SpellParameters spellParameters : spells) {
            String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParameters.spellNameId);
            if (spellName != null) {
                addMapping(spellName, new SpellParameterViewMetaEvent());
            }
        }

        reinitializeComboBox();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setEventParameter(AbstractMetaEvent metaEvent) {
        super.setEventParameter(metaEvent);
        String selectedSpellName = getSelectedModuleValue();
        Integer spellId = SpellNameTableService.INSTANCE.getSpellId(selectedSpellName);
        spellParameterEventParameter.setSpellId(spellId);

        LevelableView<SpellsView> levelableView = (LevelableView<SpellsView>) ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, SpellsView.class));
        if (levelableView != null) {
            spellParameterEventParameter.setSpellLevel(levelableView.getSelectedLevel());
        } else {
            spellParameterEventParameter.setSpellLevel(1);
        }

        metaEvent.setEventParameter(ShowSpellParameterViewEvent.class, spellParameterEventParameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<SpellViewMetaEvent> getMetaEventClass() {
        return SpellViewMetaEvent.class;
    }
}
