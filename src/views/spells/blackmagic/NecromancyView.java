package views.spells.blackmagic;

import views.spells.SpellView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.TreeMap;

public class NecromancyView {
    private JPanel mainPanel;
    private JLabel levelLabel;
    private JPanel spellsPanel;
    private JComboBox levelComboBox;
    private JComboBox spellNameComboBox;
    private JLabel spellNameLabel;

    SpellView summonSkeleton = new SpellView();
    SpellView drainLife = new SpellView();
    SpellView deathGrip = new SpellView();
    SpellView summonGoblin = new SpellView();
    SpellView resurrection = new SpellView();
    SpellView drainLifeAura = new SpellView();
    SpellView summonSpectre = new SpellView();
    SpellView trickDeath = new SpellView();
    SpellView chainDrainLife = new SpellView();
    SpellView summonBlade = new SpellView();
    SpellView controlUndead = new SpellView();
    java.util.Map<String, SpellView> spellViewMap = new TreeMap<String, SpellView>();

    public NecromancyView() {
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
        spellViewMap.put("Summon skeleton", summonSkeleton);
        spellViewMap.put("Drain life", drainLife);
        spellViewMap.put("Death grip", deathGrip);
        spellViewMap.put("Summon goblin", summonGoblin);
        spellViewMap.put("Resurrection", resurrection);
        spellViewMap.put("Drain life aura", drainLifeAura);
        spellViewMap.put("Summon spectre", summonSpectre);
        spellViewMap.put("Chain drain life", chainDrainLife);
        spellViewMap.put("Trick death", trickDeath);
        spellViewMap.put("Summon blade", summonBlade);
        spellViewMap.put("Control undead", controlUndead);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
