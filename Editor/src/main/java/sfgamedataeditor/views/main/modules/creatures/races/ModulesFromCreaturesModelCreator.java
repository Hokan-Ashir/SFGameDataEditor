package sfgamedataeditor.views.main.modules.creatures.races;

import sfgamedataeditor.views.common.model.creators.AbstractModulesModelCreator;

public class ModulesFromCreaturesModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "creatures";
    }
}
