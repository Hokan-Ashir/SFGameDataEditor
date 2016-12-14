package sfgamedataeditor.events.processing.strategies.content.modelcreators.items.scrolls;

import sfgamedataeditor.events.processing.strategies.content.modelcreators.AbstractModulesModelCreator;

public class ItemsFromScrollsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "items.spellScrolls";
    }
}
