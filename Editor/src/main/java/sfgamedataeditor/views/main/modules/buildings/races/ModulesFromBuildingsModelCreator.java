package sfgamedataeditor.views.main.modules.buildings.races;

import sfgamedataeditor.views.common.model.creators.AbstractModulesModelCreator;

public class ModulesFromBuildingsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "buildings";
    }
}
