package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.views.common.model.creators.AbstractModulesModelCreator;

public class ModulesFromSpellSchoolsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "spells";
    }
}
