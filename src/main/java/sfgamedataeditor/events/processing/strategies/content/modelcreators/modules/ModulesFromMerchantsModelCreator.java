package sfgamedataeditor.events.processing.strategies.content.modelcreators.modules;

public class ModulesFromMerchantsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "merchants";
    }
}
