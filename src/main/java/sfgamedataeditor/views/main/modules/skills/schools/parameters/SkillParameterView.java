package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.skillparameters.GUIElements;
import sfgamedataeditor.database.objects.SkillParameters;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;

public class SkillParameterView implements ControllableView {

    // format - SCHOOL LEVEL STR STA AGI DEX CHA INT WIS, each 1 byte length
    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.STRENGTH, DTOColumnNames = "strengthRequired", DTOClass = SkillParameters.class)
    private JPanel strengthPanel;

    @GUIElement(GUIElementId = GUIElements.STAMINA, DTOColumnNames = "staminaRequired", DTOClass = SkillParameters.class)
    private JPanel staminaPanel;

    @GUIElement(GUIElementId = GUIElements.AGILITY, DTOColumnNames = "agilityRequired", DTOClass = SkillParameters.class)
    private JPanel agilityPanel;

    @GUIElement(GUIElementId = GUIElements.DEXTERITY, DTOColumnNames = "dexterityRequired", DTOClass = SkillParameters.class)
    private JPanel dexterityPanel;

    @GUIElement(GUIElementId = GUIElements.INTELLIGENCE, DTOColumnNames = "intelligenceRequired", DTOClass = SkillParameters.class)
    private JPanel intelligencePanel;

    @GUIElement(GUIElementId = GUIElements.WISDOM, DTOColumnNames = "wisdomRequired", DTOClass = SkillParameters.class)
    private JPanel wisdomPanel;

    @GUIElement(GUIElementId = GUIElements.CHARISMA, DTOColumnNames = "charismaRequired", DTOClass = SkillParameters.class)
    private JPanel charismaPanel;

    @GUIElement(GUIElementId = GUIElements.SKILL_LEVEL, DTOColumnNames = "level", DTOClass = SkillParameters.class)
    private JPanel levelPanel;

    public SkillParameterView() {
        setLabelsI18nNames();
    }

    private void setLabelsI18nNames() {
        // TODO make i18n annotation, maybe via Spring framework
//        setLabelText(stregthLabel, "strength");
//        setLabelText(staminaLabel, "stamina");
//        setLabelText(dexterityLabel, "dexterity");
//        setLabelText(agilityLabel, "agility");
//        setLabelText(intelligenceLabel, "intelligence");
//        setLabelText(wisdomLabel, "wisdom");
//        setLabelText(charismaLabel, "charisma");
//        setLabelText(levelLabel, "levelLabel");
    }

    private void setLabelText(JLabel label, String not18nText) {
        label.setText(ViewTools.convertToMultiline(I18N.INSTANCE.getMessage(not18nText)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return SkillParameterController.class;
    }
}
