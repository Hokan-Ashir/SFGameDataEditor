package sfgamedataeditor.events.processing.strategies.content.modelcreators.items.weapons;

import sfgamedataeditor.events.processing.strategies.content.modelcreators.AbstractModulesModelCreator;

public class ItemsFromWeaponModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "items.weapons";
    }
}
