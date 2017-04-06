package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.views.common.model.creators.AbstractModulesModelCreator;

public class ModulesFromMerchantsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "merchants";
    }
}
