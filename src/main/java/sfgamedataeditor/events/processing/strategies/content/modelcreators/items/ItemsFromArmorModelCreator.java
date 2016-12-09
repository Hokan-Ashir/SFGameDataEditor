package sfgamedataeditor.events.processing.strategies.content.modelcreators.items;

import sfgamedataeditor.events.processing.strategies.content.modelcreators.AbstractModulesModelCreator;

public class ItemsFromArmorModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "items.armor";
    }
}
