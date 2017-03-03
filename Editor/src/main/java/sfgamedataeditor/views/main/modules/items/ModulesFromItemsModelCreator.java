package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.views.main.modules.AbstractModulesModelCreator;

public class ModulesFromItemsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "items";
    }
}