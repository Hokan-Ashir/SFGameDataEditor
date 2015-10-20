package sfgamedataeditor.views;

import javafx.util.Pair;
import sfgamedataeditor.databind.AbstractEntity;
import sfgamedataeditor.databind.FilesContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.TreeMap;

public abstract class SpellClassView extends AbstractEntity implements ILevelableView {
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
        List<Pair<String, Integer>> spellList = createSpellList();
        constructSpellViewMap(spellList);
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
                SpellClassView.this.loadDataFromFile(FilesContainer.getOriginalFile());
            }
        });
    }

    private void fillSpellNameComboBox() {
        for (String s : spellViewMap.keySet()) {
            spellNameComboBox.addItem(s);
        }
    }

    private void constructSpellViewMap(List<Pair<String, Integer>> spellList) {
        for (Pair<String, Integer> pair : spellList) {
            spellViewMap.put(pair.getKey(), new SpellView(pair.getValue()));
        }
    }

    /**
     * Creates list of pairs - "spellName" - "spellID"
     * List of spellIDs can be obtained via decompiling file "sql_spells.lua" located in
     * SPELLFORCE_FOLDER/script/ with help of Lua4Dec - https://github.com/aheadley/python-lua4dec
     * or by using this list:
     * http://pastebin.com/WmMk32zv
     *
     * @return list of pair-spells "spellName" - "spellID"
     */
    protected abstract java.util.List<Pair<String, Integer>> createSpellList();

    protected abstract byte getSpellClass();

    protected abstract byte getSpellSubClass();

    public void fillSpellsOffsetsByLevel(byte[] spellData) {
        for (SpellView spellView : spellViewMap.values()) {
            byte[] pattern = new byte[5];
            pattern[0] = (byte) (spellView.getSpellType() & 0xFF);
            pattern[1] = (byte) (spellView.getSpellType() & 0xFF00);
            pattern[2] = getSpellClass();
            pattern[3] = getSpellSubClass();
            for (int i = 0; i < 20; ++i) {
                pattern[4] = (byte) (i + 1);
                int index = indexOf(spellData, pattern);
                if (index == -1) {
                    spellView.getSpellLevelOffsets().add(i, 0L);
                } else {
                    spellView.getSpellLevelOffsets().add(i, (long) index + MainView.SPELLS_DATA_BEGIN_OFFSET - 0x2);
                }
            }
        }
    }

    /**
     * Finds the first occurrence of the pattern in the text.
     */
    private int indexOf(byte[] data, byte[] pattern) {
        int[] failure = computeFailure(pattern);

        int j = 0;
        if (data.length == 0) {
            return -1;
        }

        for (int i = 0; i < data.length; i++) {
            while (j > 0 && pattern[j] != data[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == data[i]) { j++; }
            if (j == pattern.length) {
                return i - pattern.length + 1;
            }
        }
        return -1;
    }

    /**
     * Computes the failure function using a boot-strapping process,
     * where the pattern is matched against itself.
     */
    private int[] computeFailure(byte[] pattern) {
        int[] failure = new int[pattern.length];

        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[j] != pattern[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == pattern[i]) {
                j++;
            }
            failure[i] = j;
        }

        return failure;
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
