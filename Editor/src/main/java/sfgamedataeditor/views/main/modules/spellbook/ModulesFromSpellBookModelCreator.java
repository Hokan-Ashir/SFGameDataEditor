package sfgamedataeditor.views.main.modules.spellbook;

import sfgamedataeditor.views.main.modules.AbstractModulesModelCreator;

public class ModulesFromSpellBookModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "spell.book";
    }
}
