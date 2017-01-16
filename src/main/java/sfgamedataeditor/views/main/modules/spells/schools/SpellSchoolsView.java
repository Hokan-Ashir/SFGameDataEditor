package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.database.spells.school.names.SpellSchoolNameObject;
import sfgamedataeditor.database.spells.school.names.SpellSchoolNameTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
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
    protected void fillComboBoxMapping() {
        List<SpellSchoolNameObject> allSpellSchoolNameObjects = SpellSchoolNameTableService.INSTANCE.getAllSpellSchoolNames();
        for (SpellSchoolNameObject allSpellSchoolNameObject : allSpellSchoolNameObjects) {
            addMapping(allSpellSchoolNameObject.name, SpellsView.class);
        }
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SpellSchoolsPresenter.class;
    }
}
