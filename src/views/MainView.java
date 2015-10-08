package views;

import views.skills.SkillView;
import views.spells.blackmagic.BlackMagicView;
import views.spells.blackmagic.CursesView;
import views.spells.blackmagic.DeathMagicView;
import views.spells.blackmagic.NecromancyView;
import views.spells.elementalmagic.EarthView;
import views.spells.elementalmagic.ElementalMagicView;
import views.spells.elementalmagic.FireView;
import views.spells.elementalmagic.IceView;
import views.spells.mindmagic.DefensiveMagicView;
import views.spells.mindmagic.EnchantmentView;
import views.spells.mindmagic.MindMagicView;
import views.spells.mindmagic.OffensiveMagicView;
import views.spells.whitemagic.BoonsView;
import views.spells.whitemagic.LifeView;
import views.spells.whitemagic.NatureView;
import views.spells.whitemagic.WhiteMagicView;

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

    private Map<String, Class<? extends IView>> modulesMap = new TreeMap<String, Class<? extends IView>>();

    public MainView() {
        modulesMap.put("Skills", SkillView.class);
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
