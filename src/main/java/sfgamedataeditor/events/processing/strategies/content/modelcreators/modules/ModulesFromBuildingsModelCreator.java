package sfgamedataeditor.events.processing.strategies.content.modelcreators.modules;

public class ModulesFromBuildingsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "buildings";
    }
}