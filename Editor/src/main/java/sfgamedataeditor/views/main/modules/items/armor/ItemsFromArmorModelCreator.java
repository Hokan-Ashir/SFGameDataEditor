package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.views.common.model.creators.AbstractModulesModelCreator;

public class ItemsFromArmorModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "items.armor";
    }
}
