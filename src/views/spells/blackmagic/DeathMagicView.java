package views.spells.blackmagic;

import views.spells.SpellView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.TreeMap;

public class DeathMagicView {
    private JPanel mainPanel;
    private JLabel levelLabel;
    private JPanel spellsPanel;
    private JComboBox levelComboBox;
    private JComboBox spellNameComboBox;
    private JLabel spellNameLabel;

    SpellView agony = new SpellView();
    SpellView weaknessAura = new SpellView();
    SpellView extinction = new SpellView();
    SpellView death = new SpellView();
    SpellView plague = new SpellView();
    SpellView painArea = new SpellView();
    SpellView suffocationAura = new SpellView();
    SpellView suicide = new SpellView();
    SpellView lifetap = new SpellView();
    SpellView chainAgony = new SpellView();
    SpellView torture = new SpellView();
    java.util.Map<String, SpellView> spellViewMap = new TreeMap<String, SpellView>();

    public DeathMagicView() {
        generateUI();
    }

    private void generateUI() {
        fillSpellViewMap();

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

    private void fillSpellViewMap() {
        spellViewMap.put("Agony", agony);
        spellViewMap.put("Weakness Aura", weaknessAura);
        spellViewMap.put("Extinction", extinction);
        spellViewMap.put("Death", death);
        spellViewMap.put("Plague", plague);
        spellViewMap.put("PainArea", painArea);
        spellViewMap.put("Suffocation Aura", suffocationAura);
        spellViewMap.put("Suicide", suicide);
        spellViewMap.put("Chain agony", chainAgony);
        spellViewMap.put("Lifetap", lifetap);
        spellViewMap.put("Torture", torture);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
