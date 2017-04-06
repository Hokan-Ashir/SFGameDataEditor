package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.views.common.model.creators.AbstractModulesModelCreator;

public class ModulesFromUnitsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "units";
    }
}
