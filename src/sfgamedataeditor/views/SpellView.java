package sfgamedataeditor.views;

import sfgamedataeditor.databind.*;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class SpellView extends AbstractEntity implements IView {

    private JPanel mainPanel;
    private JTextField numberField;
    private JLabel numberLabel;
    private JLabel typeLable;
    private JTextField typeField;
    private JLabel requirementClassLabel;
    private JLabel requirementSubClassLabel;
    private JLabel requirementLevelLabel;
    private JTextField requirementLevelField;
    private JLabel manaUsageLabel;
    private JTextField manaUsageField;
    private JLabel castTimeLabel;
    private JTextField castTimeField;
    private JLabel cooldownLabel;
    private JTextField cooldownField;
    private JLabel maxRangeLabel;
    private JTextField maxRangeField;
    private JLabel castTypeLabel;
    private JTextField castTypeField;
    private JLabel durationLabel;
    private JTextField durationField;
    private JLabel requirementClassLabel2;
    private JComboBox requirementClassComboBox;
    private JComboBox requirementSubClassComboBox;
    private JComboBox requirementClassComboBox2;
    private JLabel requirementSubClassLabel2;
    private JComboBox requirementSubClassComboBox2;
    private JLabel requirementLevelLabel2;
    private JTextField requirementLevelField2;
    private JLabel manaPerPeriodConsumingLabel;
    private JTextField manaPerPeriodConsumingField;
    private JLabel summoningCreatureIDLabel;
    private JTextField summoningCreatureIDField;

    private List<SpellEntity> entityList = new ArrayList<>();
    private int spellLevel = 1;
    private Map<String, List<String>> classSubClassComboBoxContent = new LinkedHashMap<>();
    private List<Long> spellLevelOffsets = new ArrayList<>(20);

    public SpellView(Integer spellType, int spellClass, int spellSubClass) {
        fillSpellLevelOffsetList(spellType, spellClass, spellSubClass);
        setOffsetInFile(spellLevelOffsets.get(spellLevel));
        initializeRequirementsComboBoxes();
        initializeEntityList();
    }

    private void fillSpellLevelOffsetList(Integer spellType, int spellClass, int spellSubClass) {
        spellLevelOffsets.add(null);
        for (int i = 1; i <= 20; i++) {
            spellLevelOffsets.add(i, 0L);
        }

        RandomAccessFile file = FilesContainer.getOriginalFile();
        int[] spellBuffer = new int[5];
        int[] pattern = new int[5];
        pattern[0] = spellType & 0xFF;
        pattern[1] = spellType & 0xFF00;
        pattern[2] = spellClass;
        pattern[3] = spellSubClass;
        for (int i = 0; i < spellLevelOffsets.size(); i++) {
            pattern[4] = i + 1;
            try {
                file.seek(0x20L);
                for (int j = 0; j < spellBuffer.length; ) {
                    spellBuffer[j] = file.readUnsignedByte();
                    if (spellBuffer[j] == pattern[j]) {
                        j++;
                    } else {
                        j = 0;
                    }

                    if (file.getFilePointer() == 0x3fd13L) {
                        break;
                    }
                }

                if (spellBuffer.length == pattern.length) {
                    spellLevelOffsets.set(i, file.getFilePointer() - 7);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeRequirementsComboBoxes() {
        // order of spell class/subclass mapping is taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=241
        // NOTE: order of this list is HIGHLY important, if you change it, you may
        // accidentally set (via this Editor) i.e "Iceburst" "White Magic - Nature" requirements
        // instead of "Elemental Magic - Ice"
        classSubClassComboBoxContent.put("Light Combat Arts", Collections.<String>emptyList());
        classSubClassComboBoxContent.put("Heavy Combat Arts", Collections.<String>emptyList());
        classSubClassComboBoxContent.put("Archery", Collections.<String>emptyList());
        classSubClassComboBoxContent.put("White Magic", new ArrayList<>(Arrays.asList("Life", "Nature", "Boons")));
        classSubClassComboBoxContent.put("Elemental Magic", new ArrayList<>(Arrays.asList("Fire", "Ice", "Earth")));
        classSubClassComboBoxContent.put("Mind Magic", new ArrayList<>(Arrays.asList("Offensive", "Enchantment", "Defensive")));
        classSubClassComboBoxContent.put("Black Magic", new ArrayList<>(Arrays.asList("Death", "Necromancy", "Curses")));

        for (String s : classSubClassComboBoxContent.keySet()) {
            requirementClassComboBox.addItem(s);
            requirementClassComboBox2.addItem(s);
        }

        attachSubClassListenerToClassComboBox(requirementClassComboBox, requirementSubClassComboBox);
        attachSubClassListenerToClassComboBox(requirementClassComboBox2, requirementSubClassComboBox2);
    }

    private void attachSubClassListenerToClassComboBox(JComboBox classComboBox, JComboBox subClassComboBox) {
        ClassRequirementComboBoxListener listener = new ClassRequirementComboBoxListener(subClassComboBox);
        classComboBox.addItemListener(listener);
        classComboBox.setSelectedItem(null);
    }

    private void initializeEntityList() {
        // inside spell offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=242
        List<EntityTuple> entityTuples = new ArrayList<EntityTuple>() {{
            add(new EntityTuple<>(numberField, 0, 2));
            add(new EntityTuple<>(typeField, 2, 2));
            add(new EntityTuple<>(requirementClassComboBox, 4, 1));
            add(new EntityTuple<>(requirementSubClassComboBox, 5, 1));
            add(new EntityTuple<>(requirementLevelField, 6, 1));
            add(new EntityTuple<>(requirementClassComboBox2, 7, 1));
            add(new EntityTuple<>(requirementSubClassComboBox2, 8, 1));
            add(new EntityTuple<>(requirementLevelField2, 9, 1));
            // TODO add possible spell skill requirements (6 bytes offset)
            add(new EntityTuple<>(manaUsageField, 16, 2));
            add(new EntityTuple<>(castTimeField, 18, 4));
            add(new EntityTuple<>(cooldownField, 22, 4));
            // TODO add possible spell min range parameter (2 bytes offset)
            add(new EntityTuple<>(maxRangeField, 28, 2));
            add(new EntityTuple<>(castTypeField, 30, 2));
            add(new EntityTuple<>(durationField, 32, 4));
            add(new EntityTuple<>(manaPerPeriodConsumingField, 36, 4));
            add(new EntityTuple<>(summoningCreatureIDField, 40, 2));
            // TODO add spell additional parameters (number of targets for wave-like spells,
            // spell-child effects for auras and frost/fire shields)
        }};

        for (EntityTuple entityTuple : entityTuples) {
            SpellEntity entity;
            if (entityTuple.component instanceof JTextField) {
                entity = new TextFieldEntity((JTextField) entityTuple.component, entityTuple.offsetInBytes, entityTuple.lengthInBytes);
            } else {
                entity = new ComboBoxEntity((JComboBox) entityTuple.component, entityTuple.offsetInBytes, entityTuple.lengthInBytes);
            }
            entity.setParent(this);
            getChildren().add(entity);
            entityList.add(entity);
        }
    }

    public void setSpellLevel(int spellLevel) {
        this.spellLevel = spellLevel;
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
    public void loadDataFromFile(RandomAccessFile file) {
        for (SpellEntity entity : entityList) {
            entity.setSpellLevel(spellLevel);
            entity.loadDataFromFile(file);
        }
    }

    private static class EntityTuple<T extends JComponent> {
        private T component;
        private long offsetInBytes;
        private int lengthInBytes;

        public EntityTuple(T component, long offsetInBytes, int lengthInBytes) {
            this.component = component;
            this.offsetInBytes = offsetInBytes;
            this.lengthInBytes = lengthInBytes;
        }
    }

    private class ClassRequirementComboBoxListener implements ItemListener {
        private JComboBox subClassComboBox;

        public ClassRequirementComboBoxListener(JComboBox subClassComboBox) {
            this.subClassComboBox = subClassComboBox;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() != ItemEvent.SELECTED) {
                return;
            }

            List<String> subClasses = classSubClassComboBoxContent.get(e.getItem());
            subClassComboBox.removeAllItems();
            for (String subClass : subClasses) {
                subClassComboBox.addItem(subClass);
            }
        }
    }
}
