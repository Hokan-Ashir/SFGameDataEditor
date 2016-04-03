package sfgamedataeditor.skills;

import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.databind.entity.AbstractLevelableEntity;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.dataextraction.ObjectToOffsetExtractor;
import sfgamedataeditor.utils.I18N;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SkillView extends AbstractLevelableEntity {
    List<SkillRequirementView> requirementViews = new ArrayList<>(ObjectToOffsetExtractor.NUMBER_OF_ABILITY_SCHOOLS);
    private JPanel mainPanel;
    private JComboBox levelComboBox;
    private JLabel levelLabel;
    private JPanel skillRequirementsPanel;

    public SkillView(Map<Integer, java.util.List<Pair<Integer, Long>>> offsets) {
        levelLabel.setText(I18N.getMessage("levelLabel"));
        for (int i = 1; i <= ObjectToOffsetExtractor.NUMBER_OF_ABILITY_SCHOOLS; i++) {
            SkillRequirementView view = new SkillRequirementView(offsets.get(i));
            view.setParent(this);
            getChildren().add(view);
            requirementViews.add(view);
        }
        generateUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void generateUI() {
        java.util.List<String> schoolNamesList = new ArrayList<>();
        schoolNamesList.add(I18N.getMessage("lightCombatArts"));
        schoolNamesList.add(I18N.getMessage("heavyCombatArts"));
        schoolNamesList.add(I18N.getMessage("archery"));
        schoolNamesList.add(I18N.getMessage("whiteMagic"));
        schoolNamesList.add(I18N.getMessage("elementalMagic"));
        schoolNamesList.add(I18N.getMessage("mindMagic"));
        schoolNamesList.add(I18N.getMessage("blackMagic"));

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
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                int level = Integer.valueOf((String) levelComboBox.getSelectedItem());
                for (SkillRequirementView requirementView : requirementViews) {
                    requirementView.setAbilityDataOffsetByLevel(level);
                }
                SkillView.this.loadDataFromFile(FilesContainer.getModificationFile());
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
