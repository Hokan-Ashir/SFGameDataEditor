package sfgamedataeditor.skills;

import javafx.util.Pair;
import sfgamedataeditor.databind.AbstractEntity;
import sfgamedataeditor.views.AbstractLevelableEntity;
import sfgamedataeditor.views.ILevelableView;
import sfgamedataeditor.views.IView;

import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class SkillView extends AbstractLevelableEntity {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SkillView() {
        generateUi();
    }

    private void generateUi() {
        java.util.List<Pair<SkillRequirementView, String>> viewList = new ArrayList<Pair<SkillRequirementView, String>>();
        viewList.add(new Pair<>(lightCombatArtsView, "Light combat arts:"));
        viewList.add(new Pair<>(heavyCombatArtsView, "Heavy combat arts:"));
        viewList.add(new Pair<>(archeryView, "Archery:"));
        viewList.add(new Pair<>(blackMagicView, "Black magic:"));
        viewList.add(new Pair<>(whiteMagicView, "White magic:"));
        viewList.add(new Pair<>(mindMagicView, "Mind magic:"));
        viewList.add(new Pair<>(elementalMagicView, "Elemental magic:"));

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

    /**
     * {@inheritDoc}
     */
    @Override
    public JComboBox getLevelComboBox() {
        return levelComboBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JLabel getLevelLabel() {
        return levelLabel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadDataFromFile(RandomAccessFile file) {

    }
}
