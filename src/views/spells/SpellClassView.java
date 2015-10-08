package views.spells;

import views.IView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.TreeMap;

public abstract class SpellClassView implements IView {
    private JPanel mainPanel;
    private JLabel levelLabel;
    private JPanel spellsPanel;
    private JComboBox levelComboBox;
    private JComboBox spellNameComboBox;
    private JLabel spellNameLabel;

    java.util.Map<String, SpellView> spellViewMap = new TreeMap<String, SpellView>();

    public SpellClassView() {
        generateUI();
    }

    private void generateUI() {
        List<String> spellList = createSpellList();
        fillSpellViewMap(spellList);

        for (String s : spellViewMap.keySet()) {
            spellNameComboBox.addItem(s);
        }

        spellNameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    spellsPanel.removeAll();

                    Object selectedItem = e.getItem();
                    SpellView view = spellViewMap.get(selectedItem);
                    if (view != null) {
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        gbc.weightx = 1.0;
                        gbc.weighty = 1.0;
                        gbc.insets = new Insets(5, 5, 5, 5);
                        gbc.anchor = GridBagConstraints.NORTHWEST;
                        spellsPanel.add(view.getMainPanel(), gbc);
                    }

                    spellsPanel.revalidate();
                    spellsPanel.repaint();
                }
            }
        });

        spellNameComboBox.setSelectedItem(null);
        spellNameComboBox.setSelectedIndex(0);
    }

    private void fillSpellViewMap(List<String> spellList) {
        for (String s : spellList) {
            spellViewMap.put(s, new SpellView());
        }
    }

    protected abstract java.util.List<String> createSpellList();

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
