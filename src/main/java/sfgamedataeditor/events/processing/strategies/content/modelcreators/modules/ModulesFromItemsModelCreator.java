package sfgamedataeditor.events.processing.strategies.content.modelcreators.modules;

public class ModulesFromItemsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "items";
    }
}