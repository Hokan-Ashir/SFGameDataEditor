package sfgamedataeditor.views;

import sfgamedataeditor.databind.FilesContainer;
import sfgamedataeditor.databind.ISpellConstraint;
import sfgamedataeditor.databind.SpellDataTuple;
import sfgamedataeditor.skills.SkillView;
import sfgamedataeditor.xml.SpellBindingExtractor;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class MainView implements IView {
    public static final int SPELL_DATA_LENGTH = 76;
    public static final int SPELL_NUMBER_DATA_LENGTH = 0x2;
    private JButton loadSfmodFileButton;
    private JButton createSfmodFileButton;
    private JComboBox<String> modulesComboBox;
    private JPanel modulesPanel;
    private JLabel modulesLabel;
    private JPanel mainPanel;

    private Map<String, AbstractLevelableEntity> modulesMap = new TreeMap<>();
    public static final int SPELLS_DATA_BEGIN_OFFSET = 0x20;
    private static final int SPELLS_DATA_END_OFFSET = 0x3fd13;

    public MainView() {
        constructModulesMap();
        fillModulesNameComboBox();
    }

    private void constructModulesMap() {
        modulesMap.put("Skills", new SkillView());

        final Map<Integer, String> spellMap = SpellBindingExtractor.getSpellMap();
        List<ISpellConstraint> constraints = getSpellDataConstraints(spellMap);
        // TODO replace with combination of original and modification file
        RandomAccessFile file = FilesContainer.getOriginalFile();
        try {
            file.seek(SPELLS_DATA_BEGIN_OFFSET);
            boolean reachedSpellDataEnd = false;
            byte[] buffer = new byte[constraints.size()];
            while (true) {
                for (int i = 0; i < constraints.size(); i++) {
                    buffer[i] = file.readByte();
                    if (constraints.get(i).isValid(buffer[i])) {
                        continue;
                    } else {
                        file.seek(file.getFilePointer() - i + 1);
                        i = -1;
                    }

                    if (file.getFilePointer() >= SPELLS_DATA_END_OFFSET) {
                        reachedSpellDataEnd = true;
                        break;
                    }
                }

                if (reachedSpellDataEnd) {
                    break;
                } else {
                    createAndAddSpellClassView(spellMap, constraints, file, buffer);

                }
            }

            constructSpellClassViews();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createAndAddSpellClassView(Map<Integer, String> spellMap,
            List<ISpellConstraint> constraints, RandomAccessFile file, byte[] buffer)
            throws IOException {
        long spellOffset = file.getFilePointer() - constraints.size() - SPELL_NUMBER_DATA_LENGTH;
        String spellViewName = constructSpellSchoolName(buffer);

        byte spellLevel = buffer[4];
        byte spellID = buffer[0];
        String spellName = spellMap.get((int) spellID & 0xFF);
        SpellDataTuple tuple = new SpellDataTuple(spellOffset,
                                                    spellLevel,
                                                    spellName,
                                                    spellID);
        if (!modulesMap.containsKey(spellViewName)) {
            modulesMap.put(spellViewName, new SpellClassView(tuple));
        } else {
            SpellClassView view = (SpellClassView) modulesMap.get(spellViewName);
            view.addSpellTuple(tuple);
        }

        file.seek(spellOffset + SPELL_DATA_LENGTH);
    }

    private void constructSpellClassViews() {
        for (AbstractLevelableEntity entity : modulesMap.values()) {
            if (!(entity instanceof SpellClassView)) {
                continue;
            }

            ((SpellClassView) entity).construct();
        }
    }

    List<ISpellConstraint> getSpellDataConstraints(final Map<Integer, String> spellMap) {
        List<ISpellConstraint> constraints = new ArrayList<>();
        addSpellTypeConstraints(spellMap, constraints);
        addSpellRequirementConstraints(constraints);
        addZeroTrailConstraints(constraints);

        // TODO try to avoid this constraint of non-zero mana usage
        // this constraint needed for correct parsing, cause in cases
        // spell number 01 00
        // spell type   01 00
        // spell school 05
        // ...
        // all constraints works and instead of getting "spell type" at first place
        // you can get spell number, which is useless for spell data binding
        addNonZeroManaUsageConstraint(constraints);

        return constraints;
    }

    private void addNonZeroManaUsageConstraint(List<ISpellConstraint> constraints) {
        constraints.add(new ISpellConstraint() {
            @Override
            public boolean isValid(byte value) {
                return value != 0;
            }
        });
    }

    private void addZeroTrailConstraints(List<ISpellConstraint> constraints) {
        for (int i = 0; i < 3; ++i) {
            constraints.add(new ISpellConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value == 0;
                }
            });
        }
    }

    private void addSpellRequirementConstraints(List<ISpellConstraint> constraints) {
        for (int i = 0; i < 3; ++i) {
            constraints.add(new ISpellConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value <= 7 && value >= 0;
                }
            });

            constraints.add(new ISpellConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value <= 3 && value >= 0;
                }
            });

            constraints.add(new ISpellConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value <= 20 && value >= 0;
                }
            });
        }
    }

    private void addSpellTypeConstraints(final Map<Integer, String> spellMap,
            List<ISpellConstraint> constraints) {
        constraints.add(new ISpellConstraint() {
            @Override
            public boolean isValid(byte value) {
                return spellMap.containsKey(value & 0xFF);
            }
        });

        constraints.add(new ISpellConstraint() {
            @Override
            public boolean isValid(byte value) {
                return value == 0;
            }
        });
    }

    private String constructSpellSchoolName(byte[] buffer) {
        String result = "";
        String spellSchoolName1 = getSpellSchoolName(buffer[2]);
        String spellSubSchoolName1 = getSpellSubSchoolName(buffer[2], buffer[3]);
        String spellSchoolName2 = getSpellSchoolName(buffer[5]);
        String spellSubSchoolName2 = getSpellSubSchoolName(buffer[5], buffer[6]);
        String spellSchoolName3 = getSpellSchoolName(buffer[8]);
        String spellSubSchoolName3 = getSpellSubSchoolName(buffer[8], buffer[9]);

        if (!spellSchoolName1.isEmpty()) {
            result += spellSchoolName1;
        }

        if (!spellSubSchoolName1.isEmpty()) {
            result += " : " + spellSubSchoolName1;
        }

        if (!spellSchoolName2.isEmpty() && !spellSchoolName1.equals(spellSchoolName2)) {
            result += "; " + spellSchoolName2;
        }

        if (!spellSubSchoolName2.isEmpty()) {
            result += " : " + spellSubSchoolName2;
        }

        if (!spellSchoolName3.isEmpty() && !spellSchoolName1.equals(spellSchoolName3)) {
            result += "; " + spellSchoolName3;
        }

        if (!spellSubSchoolName3.isEmpty()) {
            result += " : " + spellSubSchoolName3;
        }

        // TODO possibly, if ability is using by many different schools of magic,
        // it might be useful to return List/Array of "School - List<SubSchools>" pairs and add
        // parsed spells to corresponding SpellClassViews
        return result;
    }

    private String getSpellSchoolName(byte value) {
        List<String> schoolNames = new ArrayList<String>() {{
            add("");
            add("Light Combat Arts");
            add("Heavy Combat Arts");
            add("Archery");
            add("White Magic");
            add("Elemental Magic");
            add("Mind Magic");
            add("Death Magic");
        }};

        return schoolNames.get(value);
    }

    private String getSpellSubSchoolName(byte schoolValue, byte subSchoolValue) {
        Map<Integer, List<String>> subSchoolsMap = new HashMap<>();
        subSchoolsMap.put(0, new ArrayList<String>());
        subSchoolsMap.put(1, Arrays.asList("", "Piercing Weapon", "Light Blades", "Light Blunts", "Light Armor"));
        subSchoolsMap.put(2, Arrays.asList("", "Heavy Blades", "Heave Blunts", "Heavy Armor", "Shields"));
        subSchoolsMap.put(3, Arrays.asList("", "Bows", "Crossbows"));
        subSchoolsMap.put(4, Arrays.asList("", "Life", "Nature", "Boons"));
        subSchoolsMap.put(5, Arrays.asList("", "Fire", "Ice", "Earth"));
        subSchoolsMap.put(6, Arrays.asList("", "Enchantment", "Offensive", "Defensive"));
        subSchoolsMap.put(7, Arrays.asList("", "Death", "Necromancy", "Curses"));

        List<String> subSchools = subSchoolsMap.get((int) schoolValue);
        if (subSchools.isEmpty()) {
            return "";
        } else {
            return subSchools.get(subSchoolValue);
        }
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