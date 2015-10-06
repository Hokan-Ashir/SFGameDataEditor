package views;

import javax.swing.*;
import java.awt.*;

public class SkillView {
    private JPanel mainPanel;
    private JComboBox levelComboBox;
    private JLabel levelLabel;
    private JPanel skillRequrementsPanel;

    SkillRequrementView lightCombatArtsView;
    SkillRequrementView heavyCombatArtsView;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SkillView() {
        generateUi();
    }

    private void generateUi() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        levelComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        for (int i = 1; i <= 20; i++) {
            defaultComboBoxModel1.addElement(String.valueOf(i));
        }

        levelComboBox.setModel(defaultComboBoxModel1);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(levelComboBox, gbc);
        levelLabel = new JLabel();
        levelLabel.setText("Level:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(levelLabel, gbc);
        skillRequrementsPanel = new JPanel();
        skillRequrementsPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(skillRequrementsPanel, gbc);

        /*lightCombatArtsView = new SkillRequrementView();
        lightCombatArtsView.setSkillName("Light combat arts:");
        mainPanel.add(lightCombatArtsView.getMainPanel(), gbc, 0);

        heavyCombatArtsView = new SkillRequrementView();
        heavyCombatArtsView.setSkillName("Heavy combat arts:");
        mainPanel.add(heavyCombatArtsView.getMainPanel(), gbc, 1);*/
    }

}
