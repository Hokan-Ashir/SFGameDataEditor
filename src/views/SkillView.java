package views;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SkillView {
    private JPanel mainPanel;
    private JComboBox levelComboBox;
    private JLabel levelLabel;
    private JPanel skillRequrementsPanel;

    SkillRequirementView lightCombatArtsView = new SkillRequirementView();
    SkillRequirementView heavyCombatArtsView = new SkillRequirementView();
    SkillRequirementView archeryView = new SkillRequirementView();
    SkillRequirementView blackMagicView = new SkillRequirementView();
    SkillRequirementView whiteMagicView = new SkillRequirementView();
    SkillRequirementView mindMagicView = new SkillRequirementView();
    SkillRequirementView elementalMagicView = new SkillRequirementView();

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SkillView() {
        generateUi();
    }

    private void generateUi() {
        java.util.List<Pair<SkillRequirementView, String>> viewList = new ArrayList<Pair<SkillRequirementView, String>>();
        viewList.add(new Pair<SkillRequirementView, String>(lightCombatArtsView, "Light combat arts:"));
        viewList.add(new Pair<SkillRequirementView, String>(heavyCombatArtsView, "Heavy combat arts:"));
        viewList.add(new Pair<SkillRequirementView, String>(archeryView, "Archery:"));
        viewList.add(new Pair<SkillRequirementView, String>(blackMagicView, "Black magic:"));
        viewList.add(new Pair<SkillRequirementView, String>(whiteMagicView, "White magic:"));
        viewList.add(new Pair<SkillRequirementView, String>(mindMagicView, "Mind magic:"));
        viewList.add(new Pair<SkillRequirementView, String>(elementalMagicView, "Elemental magic:"));

        int gridy = 0;
        for (Pair<SkillRequirementView, String> skillRequirementViewStringPair : viewList) {
            skillRequirementViewStringPair.getKey().setSkillName(skillRequirementViewStringPair.getValue());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = gridy;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            skillRequrementsPanel.add(skillRequirementViewStringPair.getKey().getMainPanel(), gbc);

            gridy++;
        }
    }
}
