package sfgamedataeditor.events.processing.strategies.content.modelcreators;

public class ModulesFromUnitsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "units";
    }
}
