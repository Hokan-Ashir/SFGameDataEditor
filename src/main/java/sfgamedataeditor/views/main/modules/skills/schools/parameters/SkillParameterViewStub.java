package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.database.objects.SkillParameters;
import sfgamedataeditor.fieldwrapping.MappedColumn;
import sfgamedataeditor.fieldwrapping.Wrapable;
import sfgamedataeditor.views.common.IView;

import javax.swing.*;

public class SkillParameterViewStub implements Wrapable, IView {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void render() {

    }

    @Override
    public void unrender() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getWrappableClass() {
        return SkillParameters.class;
    }
}
