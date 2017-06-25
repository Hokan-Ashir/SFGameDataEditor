package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class SpellSchoolsView extends AbstractModulesView {

    public SpellSchoolsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "spellSchools"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillSubViewsMappings() {
        List<SubViewPanelTuple> mappings = SpellParametersTableService.INSTANCE.getAllSpellSchoolNames();
        addMappings(mappings, SpellsView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SpellSchoolsPresenter.class;
    }
}
