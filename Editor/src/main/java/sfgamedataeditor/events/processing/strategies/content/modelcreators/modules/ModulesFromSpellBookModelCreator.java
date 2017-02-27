package sfgamedataeditor.events.processing.strategies.content.modelcreators.modules;

import sfgamedataeditor.events.processing.strategies.content.modelcreators.AbstractModulesModelCreator;

public class ModulesFromSpellBookModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "spell.book";
    }
}
