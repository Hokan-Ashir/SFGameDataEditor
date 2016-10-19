package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.database.objects.SkillParameters;
import sfgamedataeditor.fieldwrapping.Data;
import sfgamedataeditor.fieldwrapping.MappedClass;
import sfgamedataeditor.fieldwrapping.MappedColumn;
import sfgamedataeditor.fieldwrapping.Wrapable;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.IView;

import javax.swing.*;

@MappedClass(mappedClass = SkillParameters.class)
public class SkillParameterViewStub implements Wrapable, IView {

    // format - SCHOOL LEVEL STR STA AGI DEX CHA INT WIS, each 1 byte length
    private JPanel mainPanel;

    @MappedColumn(name = "strengthRequired")
    private JTextField strengthField;
    private JLabel stregthLabel;

    @MappedColumn(name = "staminaRequired")
    private JTextField staminaField;
    private JLabel staminaLabel;

    @MappedColumn(name = "agilityRequired")
    private JTextField agilityField;
    private JLabel agilityLabel;

    @MappedColumn(name = "dexterityRequired")
    private JTextField dexterityField;
    private JLabel dexterityLabel;

    @MappedColumn(name = "charismaRequired")
    private JTextField charismaField;
    private JLabel charismaLabel;

    @MappedColumn(name = "intelligenceRequired")
    private JTextField intelligenceField;
    private JLabel intelligenceLabel;

    @MappedColumn(name = "wisdomRequired")
    private JTextField wisdomField;
    private JLabel wisdomLabel;

    private JPanel layoutPanel;

    public SkillParameterViewStub() {
        setLabelsI18nNames();
    }

    private void setLabelsI18nNames() {
        // TODO make i18n annotation, maybe via Spring framework
        stregthLabel.setText(I18N.INSTANCE.getMessage("strength"));
        staminaLabel.setText(I18N.INSTANCE.getMessage("stamina"));
        dexterityLabel.setText(I18N.INSTANCE.getMessage("dexterity"));
        agilityLabel.setText(I18N.INSTANCE.getMessage("agility"));
        intelligenceLabel.setText(I18N.INSTANCE.getMessage("intelligence"));
        wisdomLabel.setText(I18N.INSTANCE.getMessage("wisdom"));
        charismaLabel.setText(I18N.INSTANCE.getMessage("charisma"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
