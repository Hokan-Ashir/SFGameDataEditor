package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.database.spellschoolname.SpellSchoolNameObject;
import sfgamedataeditor.database.spellschoolname.SpellSchoolNameTableService;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;

import java.util.List;

public class SpellSchoolsView extends AbstractModulesView {

    public SpellSchoolsView() {
        super(I18N.INSTANCE.getMessage("spellSchools"));
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
