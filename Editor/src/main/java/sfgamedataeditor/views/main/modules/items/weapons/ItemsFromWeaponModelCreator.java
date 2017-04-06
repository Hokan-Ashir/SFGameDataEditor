package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.views.common.model.creators.AbstractModulesModelCreator;

public class ItemsFromWeaponModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "items.weapons";
    }
}
