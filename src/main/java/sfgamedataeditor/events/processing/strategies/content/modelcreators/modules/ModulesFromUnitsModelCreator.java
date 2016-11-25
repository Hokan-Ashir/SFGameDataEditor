package sfgamedataeditor.events.processing.strategies.content.modelcreators.modules;

public class ModulesFromUnitsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "units";
    }
}
