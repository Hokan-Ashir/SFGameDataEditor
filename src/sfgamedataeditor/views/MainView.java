package sfgamedataeditor.views;

import sfgamedataeditor.skills.SkillView;
import sfgamedataeditor.spells.blackmagic.BlackMagicView;
import sfgamedataeditor.spells.blackmagic.CursesView;
import sfgamedataeditor.spells.blackmagic.DeathMagicView;
import sfgamedataeditor.spells.blackmagic.NecromancyView;
import sfgamedataeditor.spells.combat.ArcheryArtsMagic;
import sfgamedataeditor.spells.combat.HeavyCombatArtsMagic;
import sfgamedataeditor.spells.combat.LightCombatArtsMagic;
import sfgamedataeditor.spells.elementalmagic.EarthView;
import sfgamedataeditor.spells.elementalmagic.ElementalMagicView;
import sfgamedataeditor.spells.elementalmagic.FireView;
import sfgamedataeditor.spells.elementalmagic.IceView;
import sfgamedataeditor.spells.mindmagic.DefensiveMagicView;
import sfgamedataeditor.spells.mindmagic.EnchantmentView;
import sfgamedataeditor.spells.mindmagic.MindMagicView;
import sfgamedataeditor.spells.mindmagic.OffensiveMagicView;
import sfgamedataeditor.spells.whitemagic.BoonsView;
import sfgamedataeditor.spells.whitemagic.LifeView;
import sfgamedataeditor.spells.whitemagic.NatureView;
import sfgamedataeditor.spells.whitemagic.WhiteMagicView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

public class MainView implements IView {
    private JButton loadSfmodFileButton;
    private JButton createSfmodFileButton;
    private JComboBox modulesComboBox;
    private JPanel modulesPanel;
    private JLabel modulesLabel;
    private JPanel mainPanel;

    private Map<String, Class<? extends IView>> modulesMap = new TreeMap<>();

    public MainView() {
        constructModulesMap();
        fillModulesNameComboBox();
    }

    private void fillModulesNameComboBox() {
        for (String s : modulesMap.keySet()) {
            modulesComboBox.addItem(s);
        }
    }

    private void constructModulesMap() {
        modulesMap.put("Skills", SkillView.class);
        modulesMap.put("Light Combat Arts", LightCombatArtsMagic.class);
        modulesMap.put("Heavy Combat Arts", HeavyCombatArtsMagic.class);
        modulesMap.put("Archery", ArcheryArtsMagic.class);
        modulesMap.put("Black magic", BlackMagicView.class);
        modulesMap.put("Black magic : Death", DeathMagicView.class);
        modulesMap.put("Black magic : Necromancy", NecromancyView.class);
        modulesMap.put("Black magic : Curses", CursesView.class);
        modulesMap.put("White magic", WhiteMagicView.class);
        modulesMap.put("White magic : Life", LifeView.class);
        modulesMap.put("White magic : Nature", NatureView.class);
        modulesMap.put("White magic : Life", BoonsView.class);
        modulesMap.put("Mind magic", MindMagicView.class);
        modulesMap.put("Mind magic : Offensive", OffensiveMagicView.class);
        modulesMap.put("Mind magic : Enchantment", EnchantmentView.class);
        modulesMap.put("Mind magic : Defensive", DefensiveMagicView.class);
        modulesMap.put("Elemental magic", ElementalMagicView.class);
        modulesMap.put("Elemental magic : Fire", FireView.class);
        modulesMap.put("Elemental magic : Ice", IceView.class);
        modulesMap.put("Elemental magic : Earth", EarthView.class);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SpellForce GameData.cff editor");
        final MainView mainView = new MainView();
        frame.setContentPane(mainView.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        mainView.getModulesComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();

                    mainView.modulesPanel.removeAll();
                    for (Map.Entry<String, Class<? extends IView>> stringClassEntry : mainView.getModulesMap().entrySet()) {
                        if (item.equals(stringClassEntry.getKey())) {
                            try {
                                IView view = stringClassEntry.getValue().getConstructor().newInstance();
                                mainView.getModulesPanel().add(view.getMainPanel());
                            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    mainView.getModulesPanel().revalidate();
                    mainView.getModulesPanel().repaint();
                }
            }
        });

        mainView.getModulesComboBox().setSelectedItem(null);
        mainView.getModulesComboBox().setSelectedIndex(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JComboBox getModulesComboBox() {
        return modulesComboBox;
    }

    public JPanel getModulesPanel() {
        return modulesPanel;
    }

    public Map<String, Class<? extends IView>> getModulesMap() {
        return modulesMap;
    }
}
