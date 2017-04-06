package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.views.common.model.creators.AbstractModulesModelCreator;

public class ModulesFromItemsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "items";
    }
}