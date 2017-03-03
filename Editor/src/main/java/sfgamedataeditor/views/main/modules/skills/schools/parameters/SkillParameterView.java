package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.skill.parameters.GUIElements;
import sfgamedataeditor.database.skill.parameters.SkillParameterObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;

import javax.swing.*;

@SuppressWarnings("unused")
public class SkillParameterView implements PresentableView {

    // format - SCHOOL LEVEL STR STA AGI DEX CHA INT WIS, each 1 byte length
    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.STRENGTH, DTOColumnNames = "strengthRequired", DTOClass = SkillParameterObject.class)
    private JPanel strengthPanel;

    @GUIElement(GUIElementId = GUIElements.STAMINA, DTOColumnNames = "staminaRequired", DTOClass = SkillParameterObject.class)
    private JPanel staminaPanel;

    @GUIElement(GUIElementId = GUIElements.AGILITY, DTOColumnNames = "agilityRequired", DTOClass = SkillParameterObject.class)
    private JPanel agilityPanel;

    @GUIElement(GUIElementId = GUIElements.DEXTERITY, DTOColumnNames = "dexterityRequired", DTOClass = SkillParameterObject.class)
    private JPanel dexterityPanel;

    @GUIElement(GUIElementId = GUIElements.INTELLIGENCE, DTOColumnNames = "intelligenceRequired", DTOClass = SkillParameterObject.class)
    private JPanel intelligencePanel;

    @GUIElement(GUIElementId = GUIElements.WISDOM, DTOColumnNames = "wisdomRequired", DTOClass = SkillParameterObject.class)
    private JPanel wisdomPanel;

    @GUIElement(GUIElementId = GUIElements.CHARISMA, DTOColumnNames = "charismaRequired", DTOClass = SkillParameterObject.class)
    private JPanel charismaPanel;

    @GUIElement(GUIElementId = GUIElements.SKILL_LEVEL, DTOColumnNames = "level", DTOClass = SkillParameterObject.class)
    private JPanel levelPanel;

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SkillParameterPresenter.class;
    }
}
