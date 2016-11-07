package sfgamedataeditor.events.processing.strategies.content.modelcreators;

public class ModulesFromItemsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "items";
    }
}