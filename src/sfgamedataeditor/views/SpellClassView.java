package sfgamedataeditor.views;

import javafx.util.Pair;
import sfgamedataeditor.databind.AbstractEntity;
import sfgamedataeditor.databind.FilesContainer;
import sfgamedataeditor.databind.SpellDataTuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
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

    java.util.Map<String, SpellView> spellViewMap = new TreeMap<>();
    private SpellView currentlySelectedSpellView;
    private Map<Pair<Integer, String>, List<Pair<Integer, Long>>> spellMap = new HashMap<>();

    public SpellClassView(SpellDataTuple tuple) {
        addSpellTuple(tuple);
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
                    String levelItem = (String) levelComboBox.getSelectedItem();
                    currentlySelectedSpellView.setSpellLevel(Integer.valueOf(levelItem));
                    possibleSpellLevels.setText("Available spell levels: " + currentlySelectedSpellView.getRangeOfPossibleSpellLevels());
                    // TODO replace with combination of original and modification file
                    SpellClassView.this.loadDataFromFile(FilesContainer.getOriginalFile());
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
                String levelItem = (String) levelComboBox.getSelectedItem();
                currentlySelectedSpellView.setSpellLevel(Integer.valueOf(levelItem));
                // TODO replace with combination of original and modification file
                SpellClassView.this.loadDataFromFile(FilesContainer.getOriginalFile());
            }
        });
    }

    private void fillSpellNameComboBox() {
        for (String s : spellViewMap.keySet()) {
            spellNameComboBox.addItem(s);
        }
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
            spellViewMap.put(pairListEntry.getKey().getValue(), new SpellView(pairListEntry.getValue(), fieldNameList));
        }

        generateUI();
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
