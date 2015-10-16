package sfgamedataeditor.views;

import sfgamedataeditor.databind.AbstractEntity;
import sfgamedataeditor.databind.ComboBoxEntity;
import sfgamedataeditor.databind.SpellEntity;
import sfgamedataeditor.databind.TextFieldEntity;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

    private List<SpellEntity> entityList = new ArrayList<>();
    private int spellLevel;
    private Map<String, List<String>> classSubClassComboBoxContent = new LinkedHashMap<>();

    public SpellView(long offsetInBytes) {
        setOffsetInFile(offsetInBytes);
        initializeRequirementsComboBoxes();
        initializeEntityList();
    }

    private void initializeRequirementsComboBoxes() {
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

        requirementClassComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                List<String> subClasses = classSubClassComboBoxContent.get(e.getItem());
                requirementSubClassComboBox.removeAllItems();
                for (String subClass : subClasses) {
                    requirementSubClassComboBox.addItem(subClass);
                }
            }
        });
        requirementClassComboBox.setSelectedItem(null);

        requirementClassComboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                List<String> subClasses = classSubClassComboBoxContent.get(e.getItem());
                requirementSubClassComboBox2.removeAllItems();
                for (String subClass : subClasses) {
                    requirementSubClassComboBox2.addItem(subClass);
                }
            }
        });
        requirementClassComboBox2.setSelectedItem(null);
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

    private class EntityTuple<T extends JComponent> {
        private T component;
        private long offsetInBytes;
        private int lengthInBytes;

        public EntityTuple(T component, long offsetInBytes, int lengthInBytes) {
            this.component = component;
            this.offsetInBytes = offsetInBytes;
            this.lengthInBytes = lengthInBytes;
        }
    }
}
