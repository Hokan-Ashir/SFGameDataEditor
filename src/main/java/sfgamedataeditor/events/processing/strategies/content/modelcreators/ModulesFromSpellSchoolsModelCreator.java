package sfgamedataeditor.events.processing.strategies.content.modelcreators;

public class ModulesFromSpellSchoolsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "spells";
    }
}