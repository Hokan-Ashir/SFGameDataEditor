package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.fieldwrapping.Data;
import sfgamedataeditor.fieldwrapping.Wrapable;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.IView;

import javax.swing.*;

public class SkillParameterViewStub implements Wrapable, IView {

    // format - SCHOOL LEVEL STR STA AGI DEX CHA INT WIS, each 1 byte length
    private JPanel mainPanel;

    @Data(offset = 2, length = 1)
    private JTextField strengthField;
    private JLabel stregthLabel;

    @Data(offset = 3, length = 1)
    private JTextField staminaField;
    private JLabel staminaLabel;

    @Data(offset = 4, length = 1)
    private JTextField agilityField;
    private JLabel agilityLabel;

    @Data(offset = 5, length = 1)
    private JTextField dexterityField;
    private JLabel dexterityLabel;

    @Data(offset = 6, length = 1)
    private JTextField charismaField;
    private JLabel charismaLabel;

    @Data(offset = 7, length = 1)
    private JTextField intelligenceField;
    private JLabel intelligenceLabel;

    @Data(offset = 8, length = 1)
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
