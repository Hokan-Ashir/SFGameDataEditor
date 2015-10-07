package views;

import javax.swing.*;

public class SkillRequirementView {
    private JPanel mainPanel;
    private JTextField strengthField;
    private JLabel stregthLabel;
    private JTextField staminaField;
    private JLabel staminaLabel;
    private JTextField agilityField;
    private JLabel agilityLabel;
    private JLabel dexterityLabel;
    private JTextField dexterityField;
    private JLabel intelligenceLabel;
    private JTextField intelligenceField;
    private JLabel wisdomLabel;
    private JTextField wisdomField;
    private JLabel charismaLabel;
    private JTextField charismaField;
    private JPanel layoutPanel;
    private JLabel skillNameLabel;
    private JSeparator skillNameSeparator;

    public void setSkillName(String skillName) {
        skillNameLabel.setText(skillName);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}