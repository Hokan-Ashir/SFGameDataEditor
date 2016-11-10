package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.database.objects.SkillParameters;
import sfgamedataeditor.fieldwrapping.MappedColumn;
import sfgamedataeditor.fieldwrapping.Wrapable;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.View;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;

public class SkillParameterViewStub implements Wrapable, View {

    // format - SCHOOL LEVEL STR STA AGI DEX CHA INT WIS, each 1 byte length
    private JPanel mainPanel;

    @MappedColumn(name = "strengthRequired", daoClass = SkillParameters.class)
    private JTextField strengthField;
    private JLabel stregthLabel;

    @MappedColumn(name = "staminaRequired", daoClass = SkillParameters.class)
    private JTextField staminaField;
    private JLabel staminaLabel;

    @MappedColumn(name = "agilityRequired", daoClass = SkillParameters.class)
    private JTextField agilityField;
    private JLabel agilityLabel;

    @MappedColumn(name = "dexterityRequired", daoClass = SkillParameters.class)
    private JTextField dexterityField;
    private JLabel dexterityLabel;

    @MappedColumn(name = "charismaRequired", daoClass = SkillParameters.class)
    private JTextField charismaField;
    private JLabel charismaLabel;

    @MappedColumn(name = "intelligenceRequired", daoClass = SkillParameters.class)
    private JTextField intelligenceField;
    private JLabel intelligenceLabel;

    @MappedColumn(name = "wisdomRequired", daoClass = SkillParameters.class)
    private JTextField wisdomField;
    private JLabel wisdomLabel;

    private JPanel layoutPanel;
    private JLabel levelLabel;
    private JComboBox levelComboBox;

    public SkillParameterViewStub() {
        setLabelsI18nNames();
    }

    private void setLabelsI18nNames() {
        // TODO make i18n annotation, maybe via Spring framework
        setLabelText(stregthLabel, "strength");
        setLabelText(staminaLabel, "stamina");
        setLabelText(dexterityLabel, "dexterity");
        setLabelText(agilityLabel, "agility");
        setLabelText(intelligenceLabel, "intelligence");
        setLabelText(wisdomLabel, "wisdom");
        setLabelText(charismaLabel, "charisma");
        setLabelText(levelLabel, "levelLabel");
    }

    private void setLabelText(JLabel label, String not18nText) {
        label.setText(ViewTools.convertToMultiline(I18N.INSTANCE.getMessage(not18nText)));
    }

    public JComboBox getLevelComboBox() {
        return levelComboBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getWrappableClass() {
        return SkillParameters.class;
    }
}
