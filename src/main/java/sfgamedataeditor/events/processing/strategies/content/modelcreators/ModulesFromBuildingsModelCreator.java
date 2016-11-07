package sfgamedataeditor.events.processing.strategies.content.modelcreators;

public class ModulesFromBuildingsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "buildings";
    }
}
