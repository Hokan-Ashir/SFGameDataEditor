package sfgamedataeditor.skills;

import javafx.util.Pair;
import sfgamedataeditor.databind.FilesContainer;
import sfgamedataeditor.databind.entity.AbstractLevelableEntity;
import sfgamedataeditor.dataextraction.ObjectToOffsetExtractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SkillView extends AbstractLevelableEntity {
    private JPanel mainPanel;
    private JComboBox levelComboBox;
    private JLabel levelLabel;
    private JPanel skillRequirementsPanel;

    List<SkillRequirementView> requirementViews = new ArrayList<>(ObjectToOffsetExtractor.NUMBER_OF_ABILITY_SCHOOLS);

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SkillView(Map<Integer, java.util.List<Pair<Integer, Long>>> offsets) {
        for (int i = 1; i <= ObjectToOffsetExtractor.NUMBER_OF_ABILITY_SCHOOLS; i++) {
            requirementViews.add(new SkillRequirementView(offsets.get(i)));
        }
        generateUI();
    }

    private void generateUI() {
        java.util.List<String> schoolNamesList = new ArrayList<>();
        schoolNamesList.add("Light combat arts:");
        schoolNamesList.add("Heavy combat arts:");
        schoolNamesList.add("Archery:");
        schoolNamesList.add("White magic:");
        schoolNamesList.add("Elemental magic:");
        schoolNamesList.add("Mind magic:");
        schoolNamesList.add("Black magic:");

        int gridy = 0;
        for (int i = 0; i < requirementViews.size(); i++) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = gridy;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            requirementViews.get(i).setSkillName(schoolNamesList.get(i));
            skillRequirementsPanel.add(requirementViews.get(i).getMainPanel(), gbc);

            gridy++;
        }

        levelComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int level = Integer.valueOf((String) levelComboBox.getSelectedItem());
                for (SkillRequirementView requirementView : requirementViews) {
                    requirementView.setAbilityLevel(level);
                }
                // TODO replace with combination of original and modification file
                SkillView.this.loadDataFromFile(FilesContainer.getOriginalFile());
            }
        });
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
        for (SkillRequirementView requirementView : requirementViews) {
            requirementView.loadDataFromFile(file);
        }
    }
}
