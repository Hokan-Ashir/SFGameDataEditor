package sfgamedataeditor.events.processing.strategies.content.modelcreators;

public class ModulesFromMerchantsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "merchants";
    }
}
