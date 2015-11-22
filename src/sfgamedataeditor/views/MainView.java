package sfgamedataeditor.views;

import javafx.util.Pair;
import sfgamedataeditor.databind.IDataConstraint;
import sfgamedataeditor.databind.entity.AbstractLevelableEntity;
import sfgamedataeditor.databind.files.FileData;
import sfgamedataeditor.databind.files.FileUtils;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.dataextraction.ObjectToOffsetExtractor;
import sfgamedataeditor.dataextraction.XMLExtractor;
import sfgamedataeditor.skills.SkillView;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class MainView implements IView {
    private static final int SPELLS_DATA_BEGIN_OFFSET = 0x20;
    private static final int SPELLS_DATA_END_OFFSET = 0x3fd13;
    private static final int SPELL_DATA_LENGTH = 76;
    private static final int SPELL_NUMBER_DATA_LENGTH = 0x2;

    private JButton loadSfmodFileButton;
    private JButton createSfmodFileButton;
    private JComboBox<String> modulesComboBox;
    private JPanel modulesPanel;
    private JLabel modulesLabel;
    private JPanel mainPanel;

    private Map<String, AbstractLevelableEntity> modulesMap = new TreeMap<>();
    private AbstractLevelableEntity currentSelectedView;

    public MainView() {
        loadSfmodFileButton.setText(XMLExtractor.getTagValue("loadSfModFileButtonCaption"));
        createSfmodFileButton.setText(XMLExtractor.getTagValue("createSfModFileButtonCaption"));
        modulesLabel.setText(XMLExtractor.getTagValue("modulesListLabel"));

        attachLoadSfmodFileButtonListener();
        attachCreateSfmodFileButtonListener();

        constructModulesMap();
        fillModulesNameComboBox();
    }

    private void attachCreateSfmodFileButtonListener() {
        createSfmodFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String sfModFileName = JOptionPane.showInputDialog(null, XMLExtractor.getTagValue("newSfModFileName"), XMLExtractor.getTagValue("newSfModFileCreationCaption"), JOptionPane.QUESTION_MESSAGE);
                if (sfModFileName != null && !sfModFileName.isEmpty()) {
                    String messageCaption = XMLExtractor.getTagValue("message");
                    String notificationMassage = XMLExtractor.getTagValue("processingSfModFile") + sfModFileName + XMLExtractor.getTagValue("processingCreation")
                            + "\n" + XMLExtractor.getTagValue("closeMessageWindowProposition");
                    JOptionPane.showMessageDialog(null, notificationMassage, messageCaption, JOptionPane.INFORMATION_MESSAGE);
                    ViewTools.setComponentsEnableStatus(mainPanel, false);

                    // TODO add notifications about modification file creation processing (NOT via dialog windows)
                    FileUtils.createSfModFile(sfModFileName);
                    String successfulMessage = XMLExtractor.getTagValue("sfmodFilePrefix") + sfModFileName + XMLExtractor.getTagValue("successfullyCreated");
                    JOptionPane.showMessageDialog(null, successfulMessage, messageCaption, JOptionPane.INFORMATION_MESSAGE);

                    ViewTools.setComponentsEnableStatus(mainPanel, true);
                }
            }
        });
    }

    private void attachLoadSfmodFileButtonListener() {
        loadSfmodFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileFilter fileFilter = new FileNameExtensionFilter(
                        XMLExtractor.getTagValue("sfmodFilesDescription"), FileSelectionView.SFMOD_FILE_EXTENSION);
                chooser.setFileFilter(fileFilter);
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.showOpenDialog(mainPanel);
                File selectedFile = chooser.getSelectedFile();
                if (selectedFile == null) {
                    return;
                }

                RandomAccessFile file;
                try {
                    file = new RandomAccessFile(selectedFile.getAbsolutePath(), "r");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                    return;
                }

                FilesContainer.setModificationFile(new FileData(file, selectedFile.getParent() + File.separator, selectedFile.getName()));

                String messageCaption = XMLExtractor.getTagValue("message");
                String notificationMassage = XMLExtractor.getTagValue("processingSfModFile") + selectedFile.getName() + XMLExtractor.getTagValue("processingLoading")
                        + "\n" + XMLExtractor.getTagValue("closeMessageWindowProposition");
                JOptionPane.showMessageDialog(null, notificationMassage, messageCaption, JOptionPane.INFORMATION_MESSAGE);
                ViewTools.setComponentsEnableStatus(mainPanel, false);

                // TODO add notifications about modification file loading process (NOT via dialog windows)
                FileUtils.createTemporaryModificationFile();
                currentSelectedView.loadDataFromFile(FilesContainer.getModificationFile());


                String successfulMessage = XMLExtractor.getTagValue("sfmodFilePrefix") + FilesContainer.getModificationFileName() + XMLExtractor.getTagValue("successfullyLoaded");
                JOptionPane.showMessageDialog(null, successfulMessage, messageCaption, JOptionPane.INFORMATION_MESSAGE);
                ViewTools.setComponentsEnableStatus(mainPanel, true);
            }
        });
    }

    private void constructModulesMap() {
        Map<Integer, List<Pair<Integer, Long>>> skillOffsets = ObjectToOffsetExtractor.getSkillSchoolsOffsets();
        modulesMap.put(XMLExtractor.getTagValue("skillsView"), new SkillView(skillOffsets));

        final Map<Integer, Pair<String, List<String>>> spellMap = XMLExtractor.getSpellMap();
        List<IDataConstraint> constraints = getSpellDataConstraints(spellMap);
        RandomAccessFile file = FilesContainer.getModificationFile();
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
                    createAndAddSpellClassView(spellMap, constraints.size(), file, buffer);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        constructSpellClassViews(spellMap);
    }

    private void createAndAddSpellClassView(Map<Integer, Pair<String, List<String>>> spellMap, int constraintsSize,
            RandomAccessFile file, byte[] buffer)
            throws IOException {
        long spellOffset = file.getFilePointer() - constraintsSize - SPELL_NUMBER_DATA_LENGTH;
        String spellViewName = constructSpellSchoolName(buffer);

        byte spellLevel = buffer[4];
        int spellID = buffer[0] & 0xFF;
        String spellName = spellMap.get(spellID).getKey();
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

    private void constructSpellClassViews(Map<Integer, Pair<String, List<String>>> spellMap) {
        for (AbstractLevelableEntity entity : modulesMap.values()) {
            if (!(entity instanceof SpellClassView)) {
                continue;
            }

            ((SpellClassView) entity).construct(spellMap);
        }
    }

    List<IDataConstraint> getSpellDataConstraints(final Map<Integer, Pair<String, List<String>>> spellMap) {
        List<IDataConstraint> constraints = new ArrayList<>();
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

    private void addNonZeroManaUsageConstraint(List<IDataConstraint> constraints) {
        constraints.add(new IDataConstraint() {
            @Override
            public boolean isValid(byte value) {
                return value != 0;
            }
        });
    }

    private void addZeroTrailConstraints(List<IDataConstraint> constraints) {
        for (int i = 0; i < 3; ++i) {
            constraints.add(new IDataConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value == 0;
                }
            });
        }
    }

    private void addSpellRequirementConstraints(List<IDataConstraint> constraints) {
        for (int i = 0; i < 3; ++i) {
            constraints.add(new IDataConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value <= ObjectToOffsetExtractor.NUMBER_OF_ABILITY_SCHOOLS && value >= 0;
                }
            });

            constraints.add(new IDataConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value <= ObjectToOffsetExtractor.NUMBER_OF_ABILITY_SUBSCHOOLS && value >= 0;
                }
            });

            constraints.add(new IDataConstraint() {
                @Override
                public boolean isValid(byte value) {
                    return value <= ObjectToOffsetExtractor.NUMBER_OF_ABILITY_LEVELS && value >= 0;
                }
            });
        }
    }

    private void addSpellTypeConstraints(final Map<Integer, Pair<String, List<String>>> spellMap,
                                         List<IDataConstraint> constraints) {
        constraints.add(new IDataConstraint() {
            @Override
            public boolean isValid(byte value) {
                return spellMap.containsKey(value & 0xFF);
            }
        });

        constraints.add(new IDataConstraint() {
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
        // parsed spells to corresponding SpellClassViews, this, however require synchronization,
        // between different SpellClassViews, which can be hard to achieve
        return result;
    }

    private String getSpellSchoolName(byte value) {
        List<String> schoolNames = new ArrayList<String>() {{
            add("");
            add(XMLExtractor.getTagValue("lightCombatArts"));
            add(XMLExtractor.getTagValue("heavyCombatArts"));
            add(XMLExtractor.getTagValue("archery"));
            add(XMLExtractor.getTagValue("whiteMagic"));
            add(XMLExtractor.getTagValue("elementalMagic"));
            add(XMLExtractor.getTagValue("mindMagic"));
            add(XMLExtractor.getTagValue("blackMagic"));
        }};

        return schoolNames.get(value);
    }

    private String getSpellSubSchoolName(byte schoolValue, byte subSchoolValue) {
        Map<Integer, List<String>> subSchoolsMap = new HashMap<>();
        subSchoolsMap.put(0, new ArrayList<String>());
        subSchoolsMap.put(1, Arrays.asList("", XMLExtractor.getTagValue("piecingWeapon"), XMLExtractor.getTagValue("lightBlades"),
                XMLExtractor.getTagValue("lightBlunts"), XMLExtractor.getTagValue("lightArmor")));
        subSchoolsMap.put(2, Arrays.asList("", XMLExtractor.getTagValue("heavyBlades"), XMLExtractor.getTagValue("heavyBlunts"),
                XMLExtractor.getTagValue("heavyArmor"), XMLExtractor.getTagValue("shields")));
        subSchoolsMap.put(3, Arrays.asList("", XMLExtractor.getTagValue("bows"), XMLExtractor.getTagValue("crossbows")));
        subSchoolsMap.put(4, Arrays.asList("", XMLExtractor.getTagValue("life"), XMLExtractor.getTagValue("nature"),
                XMLExtractor.getTagValue("boons")));
        subSchoolsMap.put(5, Arrays.asList("", XMLExtractor.getTagValue("fire"), XMLExtractor.getTagValue("ice"),
                XMLExtractor.getTagValue("earth")));
        subSchoolsMap.put(6, Arrays.asList("", XMLExtractor.getTagValue("enchantment"), XMLExtractor.getTagValue("offensive"),
                XMLExtractor.getTagValue("defensive")));
        subSchoolsMap.put(7, Arrays.asList("", XMLExtractor.getTagValue("death"), XMLExtractor.getTagValue("necromancy"),
                XMLExtractor.getTagValue("curses")));

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
        JFrame frame = new JFrame(XMLExtractor.getTagValue("sfmodFilesCreationWindowCaption"));
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
                            mainView.setCurrentSelectedView(view);
                            view.loadDataFromFile(FilesContainer.getModificationFile());
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

    public void setCurrentSelectedView(AbstractLevelableEntity currentSelectedView) {
        this.currentSelectedView = currentSelectedView;
    }
}
