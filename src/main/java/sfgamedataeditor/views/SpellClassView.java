package sfgamedataeditor.views;

import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.databind.entity.AbstractLevelableEntity;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.dataextraction.XMLExtractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.List;

public class SpellClassView extends AbstractLevelableEntity {
    private JPanel mainPanel;
    private JLabel levelLabel;
    private JPanel spellsPanel;
    private JComboBox<Integer> levelComboBox;
    private JComboBox<String> spellNameComboBox;
    private JLabel spellNameLabel;
    private JLabel possibleSpellLevels;

    private Map<String, SpellView> spellViewMap = new TreeMap<>();
    private SpellView currentlySelectedSpellView;
    private Map<Pair<Integer, String>, List<Pair<Integer, Long>>> spellMap = new HashMap<>();

    public SpellClassView(SpellDataTuple tuple) {
        levelLabel.setText(XMLExtractor.getTagValue("levelLabel"));
        spellNameLabel.setText(XMLExtractor.getTagValue("spellNameLabel"));
        possibleSpellLevels.setText(XMLExtractor.getTagValue("possibleAbilityLevels"));

        addSpellTuple(tuple);
    }

    public void construct(Map<Integer, Pair<String, List<String>>> spellBindMap) {
        for (Map.Entry<Pair<Integer, String>, List<Pair<Integer, Long>>> pairListEntry : spellMap
                .entrySet()) {
            Pair<String, List<String>> spellNameFieldNamePair = spellBindMap.get(pairListEntry.getKey().getKey());
            List<String> fieldNameList;
            if (spellNameFieldNamePair == null) {
                fieldNameList = Collections.emptyList();
            } else {
                fieldNameList = spellNameFieldNamePair.getValue();
            }
            SpellView view = new SpellView(pairListEntry.getValue(), fieldNameList);
            getChildren().add(view);
            view.setParent(this);
            spellViewMap.put(pairListEntry.getKey().getValue(), view);
        }

        generateUI();
    }

    private void generateUI() {
        fillSpellNameComboBox();

        spellNameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                spellsPanel.removeAll();

                Object selectedItem = e.getItem();
                currentlySelectedSpellView = spellViewMap.get(selectedItem);
                if (currentlySelectedSpellView != null) {
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.weightx = 1.0;
                    gbc.weighty = 1.0;
                    gbc.insets = new Insets(5, 5, 5, 5);
                    gbc.anchor = GridBagConstraints.NORTHWEST;
                    spellsPanel.add(currentlySelectedSpellView.getMainPanel(), gbc);
                    String possibleAbilityLevels = XMLExtractor.getTagValue("possibleAbilityLevels") + currentlySelectedSpellView.getRangeOfPossibleSpellLevels();
                    possibleSpellLevels.setText(possibleAbilityLevels);

                    String levelItem = (String) levelComboBox.getSelectedItem();
                    Integer abilityLevel = Integer.valueOf(levelItem);
                    if (!currentlySelectedSpellView.isAbilityHasLevel(abilityLevel)) {
                        currentlySelectedSpellView.hideViewComponents();
                        return;
                    }

                    currentlySelectedSpellView.setAbilityDataOffsetByLevel(Integer.valueOf(levelItem));
                    SpellClassView.this.loadDataFromFile(FilesContainer.getModificationFile());
                }

                spellsPanel.revalidate();
                spellsPanel.repaint();
            }
        });

        spellNameComboBox.setSelectedItem(null);
        spellNameComboBox.setSelectedIndex(0);

        levelComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                String levelItem = (String) levelComboBox.getSelectedItem();
                Integer abilityLevel = Integer.valueOf(levelItem);
                if (!currentlySelectedSpellView.isAbilityHasLevel(abilityLevel)) {
                    currentlySelectedSpellView.hideViewComponents();
                    return;
                }

                currentlySelectedSpellView.setAbilityDataOffsetByLevel(abilityLevel);
                SpellClassView.this.loadDataFromFile(FilesContainer.getModificationFile());
            }
        });
    }

    private void fillSpellNameComboBox() {
        for (String s : spellViewMap.keySet()) {
            spellNameComboBox.addItem(s);
        }
    }

    public void addSpellTuple(SpellDataTuple tuple) {
        Pair<Integer, String> spellKey = new Pair<>(tuple.getId(), tuple.getName());
        if (!spellMap.containsKey(spellKey)) {
            List<Pair<Integer, Long>> spellValue = new ArrayList<>();
            spellValue.add(new Pair<>(tuple.getLevel(), tuple.getOffset()));
            spellMap.put(spellKey, spellValue);
        } else {
            List<Pair<Integer, Long>> spellValue = spellMap.get(spellKey);
            spellValue.add(new Pair<>(tuple.getLevel(), tuple.getOffset()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadDataFromFile(RandomAccessFile file) {
        currentlySelectedSpellView.loadDataFromFile(file);
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
}
