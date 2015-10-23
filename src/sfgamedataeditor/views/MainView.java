package sfgamedataeditor.views;

import sfgamedataeditor.databind.AbstractEntity;
import sfgamedataeditor.databind.FilesContainer;
import sfgamedataeditor.skills.SkillView;
import sfgamedataeditor.spells.blackmagic.*;
import sfgamedataeditor.spells.combat.ArcheryArtsMagic;
import sfgamedataeditor.spells.combat.HeavyCombatArtsMagic;
import sfgamedataeditor.spells.combat.LightCombatArtsMagic;
import sfgamedataeditor.spells.elementalmagic.*;
import sfgamedataeditor.spells.mindmagic.*;
import sfgamedataeditor.spells.whitemagic.*;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class MainView implements IView {
    private JButton loadSfmodFileButton;
    private JButton createSfmodFileButton;
    private JComboBox modulesComboBox;
    private JPanel modulesPanel;
    private JLabel modulesLabel;
    private JPanel mainPanel;

    private Map<String, AbstractLevelableEntity> modulesMap = new LinkedHashMap<>();
    public static final int SPELLS_DATA_BEGIN_OFFSET = 0x20;
    private static final int SPELLS_DATA_END_OFFSET = 0x3fd13;

    public MainView() {
        constructModulesMap();
        fillModulesNameComboBox();
    }

    private void constructModulesMap() {
        modulesMap.put("Skills", new SkillView());
        modulesMap.put("Light Combat Arts", new LightCombatArtsMagic());
        modulesMap.put("Heavy Combat Arts", new HeavyCombatArtsMagic());
        modulesMap.put("Archery", new ArcheryArtsMagic());
        modulesMap.put("White magic", new WhiteMagicView());
        modulesMap.put("White magic : All", new WhiteMagicAllView());
        modulesMap.put("White magic : Life", new LifeView());
        modulesMap.put("White magic : Nature", new NatureView());
        modulesMap.put("White magic : Boons", new BoonsView());
        modulesMap.put("Elemental magic", new ElementalMagicView());
        modulesMap.put("Elemental magic : All", new ElementalMagicAllView());
        modulesMap.put("Elemental magic : Fire", new FireView());
        modulesMap.put("Elemental magic : Ice", new IceView());
        modulesMap.put("Elemental magic : Earth", new EarthView());
        modulesMap.put("Mind magic", new MindMagicView());
        modulesMap.put("Mind magic : All", new MindMagicAllView());
        modulesMap.put("Mind magic : Offensive", new OffensiveMagicView());
        modulesMap.put("Mind magic : Enchantment", new EnchantmentView());
        modulesMap.put("Mind magic : Defensive", new DefensiveMagicView());
        modulesMap.put("Black magic", new BlackMagicView());
        modulesMap.put("Black magic : All", new BlackMagicAllView());
        modulesMap.put("Black magic : Death", new DeathMagicView());
        modulesMap.put("Black magic : Necromancy", new NecromancyView());
        modulesMap.put("Black magic : Curses", new CursesView());

        setUpSpellsOffsetsByLevel();
    }

    private void setUpSpellsOffsetsByLevel() {
        byte[] buffer = getSpellDataArrayFromFile();
        for (IView view : modulesMap.values()) {
            if (!(view instanceof SpellClassView)) {
                continue;
            }

            ((SpellClassView) view).fillSpellsOffsetsByLevel(buffer);
        }
    }

    private byte[] getSpellDataArrayFromFile() {
        RandomAccessFile file = FilesContainer.getOriginalFile();
        byte[] buffer = new byte[SPELLS_DATA_END_OFFSET - SPELLS_DATA_BEGIN_OFFSET];
        try {
            file.seek(SPELLS_DATA_BEGIN_OFFSET);
            file.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer;
    }

    private void fillModulesNameComboBox() {
        for (String s : modulesMap.keySet()) {
            modulesComboBox.addItem(s);
        }
    }

    public static void showMainView() {
        JFrame frame = new JFrame("SpellForce GameData.cff Editor : Parameters Editor");
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

                    mainView.getModulesPanel().removeAll();
                    for (Map.Entry<String, AbstractLevelableEntity> stringClassEntry : mainView.getModulesMap().entrySet()) {
                        if (item.equals(stringClassEntry.getKey())) {
                            AbstractLevelableEntity view = stringClassEntry.getValue();
                            view.loadDataFromFile(FilesContainer.getOriginalFile());
                            mainView.getModulesPanel().add(view.getMainPanel());
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

    public Map<String, AbstractLevelableEntity> getModulesMap() {
        return modulesMap;
    }
}
