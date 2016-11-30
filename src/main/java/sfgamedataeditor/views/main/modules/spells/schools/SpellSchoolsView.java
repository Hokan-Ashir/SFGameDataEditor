package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.database.spellschoolname.SpellSchoolNameObject;
import sfgamedataeditor.database.spellschoolname.SpellSchoolNameTableService;
import sfgamedataeditor.mvc.objects.AbstractController;
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
    public Class<? extends AbstractController> getControllerClass() {
        return SpellSchoolsController.class;
    }
}
