package sfgamedataeditor.views;

import javafx.util.Pair;
import sfgamedataeditor.databind.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.List;

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
    private JComboBox<String> requirementClassComboBox;
    private JComboBox<String> requirementSubClassComboBox;
    private JComboBox<String> requirementClassComboBox2;
    private JLabel requirementSubClassLabel2;
    private JComboBox<String> requirementSubClassComboBox2;
    private JLabel requirementLevelLabel2;
    private JTextField requirementLevelField2;
    private JLabel manaPerPeriodConsumingLabel;
    private JTextField manaPerPeriodConsumingField;
    private JLabel summoningCreatureIDLabel;
    private JTextField summoningCreatureIDField;
    private JLabel requirementClassLabel3;
    private JComboBox<String> requirementClassComboBox3;
    private JLabel requirementSubClassLabel3;
    private JComboBox<String> requirementSubClassComboBox3;
    private JLabel requirementLevelLabel3;
    private JTextField requirementLevelField3;
    private JLabel minRangeLabel;
    private JTextField minRangeField;

    public static final int NUMBER_OF_ABILITY_LEVELS = 20;

    private List<Entity> entityList = new ArrayList<>();
    private Map<String, List<String>> classSubClassComboBoxContent = new LinkedHashMap<>();
    private List<Pair<Integer, Long>> spellLevelOffsets = new ArrayList<>(NUMBER_OF_ABILITY_LEVELS);

    public SpellView(List<Pair<Integer, Long>> offsets) {
        this.spellLevelOffsets = offsets;
        this.spellLevelOffsets.sort(new Comparator<Pair<Integer, Long>>() {
            @Override
            public int compare(Pair<Integer, Long> o1, Pair<Integer, Long> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        initializeRequirementsComboBoxes();
        initializeEntityList();
    }

    private void initializeRequirementsComboBoxes() {
        // order of spell class/subclass mapping is taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=241
        // NOTE: order of this list is HIGHLY important, if you change it, you may
        // accidentally set (via this Editor) i.e "Iceburst" "White Magic - Nature" requirements
        // instead of "Elemental Magic - Ice"
        classSubClassComboBoxContent.put("", Arrays.asList(""));
        classSubClassComboBoxContent.put("Light Combat Arts", Arrays.asList("", "Piercing Weapon",
                "Light Blades", "Light Blunts", "Light Armor"));
        classSubClassComboBoxContent.put("Heavy Combat Arts", Arrays.asList("", "Heavy Blades",
                "Heave Blunts", "Heavy Armor", "Shields"));
        classSubClassComboBoxContent.put("Archery", Arrays.asList("", "Bows", "Crossbows"));
        classSubClassComboBoxContent.put("White Magic", Arrays.asList("", "Life", "Nature", "Boons"));
        classSubClassComboBoxContent.put("Elemental Magic", Arrays.asList("", "Fire", "Ice", "Earth"));
        classSubClassComboBoxContent.put("Mind Magic", Arrays.asList("", "Enchantment", "Offensive", "Defensive"));
        classSubClassComboBoxContent.put("Black Magic", Arrays.asList("", "Death", "Necromancy", "Curses"));

        for (String s : classSubClassComboBoxContent.keySet()) {
            requirementClassComboBox.addItem(s);
            requirementClassComboBox2.addItem(s);
            requirementClassComboBox3.addItem(s);
        }

        attachSubClassListenerToClassComboBox(requirementClassComboBox, requirementSubClassComboBox);
        attachSubClassListenerToClassComboBox(requirementClassComboBox2, requirementSubClassComboBox2);
        attachSubClassListenerToClassComboBox(requirementClassComboBox3, requirementSubClassComboBox3);
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
            add(new EntityTuple<>(requirementClassComboBox3, 10, 1));
            add(new EntityTuple<>(requirementSubClassComboBox3, 11, 1));
            add(new EntityTuple<>(requirementLevelField3, 12, 1));
            // TODO add possible spell skill requirements (3 bytes offset)
            add(new EntityTuple<>(manaUsageField, 16, 2));
            add(new EntityTuple<>(castTimeField, 18, 4));
            add(new EntityTuple<>(cooldownField, 22, 4));
            add(new EntityTuple<>(minRangeField, 26, 2));
            add(new EntityTuple<>(maxRangeField, 28, 2));
            add(new EntityTuple<>(castTypeField, 30, 2));
            add(new EntityTuple<>(durationField, 32, 4));
            add(new EntityTuple<>(manaPerPeriodConsumingField, 36, 4));
            add(new EntityTuple<>(summoningCreatureIDField, 40, 2));
            // TODO add spell additional parameters (number of targets for wave-like spells,
            // spell-child effects for auras and frost/fire shields)
        }};

        for (EntityTuple entityTuple : entityTuples) {
            Entity entity;
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
        if (spellLevelOffsets.isEmpty()) {
            return;
        }

        Long offsetInFile = 0l;
        for (Pair<Integer, Long> spellLevelOffset : spellLevelOffsets) {
            if (spellLevelOffset.getKey() == spellLevel) {
                offsetInFile = spellLevelOffset.getValue();
                break;
            }
        }

        setOffsetInFile(offsetInFile);
        for (Component component : mainPanel.getComponents()) {
            component.setVisible(offsetInFile != 0);
        }
    }

    public String getRangeOfPossibleSpellLevels() {
        String result;
        if (spellLevelOffsets.size() == 1) {
            result = String.valueOf(spellLevelOffsets.get(0).getKey());
        } else if (spellLevelOffsets.size() > 1) {
            result = String.valueOf(spellLevelOffsets.get(0).getKey());
            result += "-";
            result += String.valueOf(spellLevelOffsets.get(spellLevelOffsets.size() - 1).getKey());
        } else {
            result = "";
        }

        return result;
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
        for (Entity entity : entityList) {
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