package sfgamedataeditor.events.processing.strategies.content.modelcreators;

public class ModulesFromSkillSchoolsModelCreator extends AbstractModulesModelCreator {
    @Override
    protected String createChildModuleNon18nName() {
        return "skills";
    }
}
