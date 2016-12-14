package sfgamedataeditor.events.processing.strategies.content.modelcreators.items.miscellaneous;

import sfgamedataeditor.events.processing.strategies.content.modelcreators.AbstractModulesModelCreator;

public class ItemsFromMiscellaneousModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "miscellaneousTypes";
    }
}
