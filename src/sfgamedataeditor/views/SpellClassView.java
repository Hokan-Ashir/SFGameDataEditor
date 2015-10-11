package sfgamedataeditor.views;

import javafx.util.Pair;
import sfgamedataeditor.databind.AbstractEntity;
import sfgamedataeditor.databind.FilesContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.TreeMap;

public abstract class SpellClassView extends AbstractEntity implements IView {
    private JPanel mainPanel;
    private JLabel levelLabel;
    private JPanel spellsPanel;
    private JComboBox levelComboBox;
    private JComboBox spellNameComboBox;
    private JLabel spellNameLabel;

    java.util.Map<String, SpellView> spellViewMap = new TreeMap<>();
    private SpellView currentlySelectedSpellView;

    public SpellClassView() {
        generateUI();
    }

    private void generateUI() {
        List<Pair<String, Long>> spellList = createSpellList();
        constructSpellViewMap(spellList);
        fillSpellNameComboBox();

        spellNameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
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
                        SpellClassView.this.loadDataFromFile(FilesContainer.getOriginalFile());
                    }

                    spellsPanel.revalidate();
                    spellsPanel.repaint();
                }
            }
        });

        spellNameComboBox.setSelectedItem(null);
        spellNameComboBox.setSelectedIndex(0);

        levelComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String levelItem = (String) levelComboBox.getSelectedItem();
                currentlySelectedSpellView.setSpellLevel(Integer.valueOf(levelItem));
                SpellClassView.this.loadDataFromFile(FilesContainer.getOriginalFile());
            }
        });
    }

    private void fillSpellNameComboBox() {
        for (String s : spellViewMap.keySet()) {
            spellNameComboBox.addItem(s);
        }
    }

    private void constructSpellViewMap(List<Pair<String, Long>> spellList) {
        for (Pair<String, Long> pair : spellList) {
            spellViewMap.put(pair.getKey(), new SpellView(pair.getValue()));
        }
    }

    protected abstract java.util.List<Pair<String, Long>> createSpellList();

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
}
