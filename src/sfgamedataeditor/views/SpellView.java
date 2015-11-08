package sfgamedataeditor.views;

import javafx.util.Pair;
import sfgamedataeditor.databind.entity.EntityContainer;
import sfgamedataeditor.databind.entity.EntityTuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

public class SpellView extends EntityContainer implements IView {
    private static final int LABEL_LINE_MAX_LENGTH = 12;

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
    private JLabel parameterLabel1;
    private JTextField parameterField1;
    private JLabel requirementClassLabel2;
    private JComboBox<String> requirementClassComboBox;
    private JComboBox<String> requirementSubClassComboBox;
    private JComboBox<String> requirementClassComboBox2;
    private JLabel requirementSubClassLabel2;
    private JComboBox<String> requirementSubClassComboBox2;
    private JLabel requirementLevelLabel2;
    private JTextField requirementLevelField2;
    private JLabel parameterLabel2;
    private JTextField parameterField2;
    private JLabel parameterLabel3;
    private JTextField parameterField3;
    private JLabel requirementClassLabel3;
    private JComboBox<String> requirementClassComboBox3;
    private JLabel requirementSubClassLabel3;
    private JComboBox<String> requirementSubClassComboBox3;
    private JLabel requirementLevelLabel3;
    private JTextField requirementLevelField3;
    private JLabel minRangeLabel;
    private JTextField minRangeField;
    private JLabel parameterLabel4;
    private JTextField parameterField4;
    private JLabel parameterLabel5;
    private JTextField parameterField5;
    private JLabel parameterLabel6;
    private JTextField parameterField6;
    private JLabel parameterLabel7;
    private JTextField parameterField7;
    private JLabel parameterLabel8;
    private JTextField parameterField8;
    private JLabel parameterLabel9;
    private JTextField parameterField9;
    private JLabel parameterLabel10;
    private JTextField parameterField10;

    private Map<String, List<String>> classSubClassComboBoxContent = new LinkedHashMap<>();

    public SpellView(List<Pair<Integer, Long>> offsets, List<String> fieldNamesList) {
        super(offsets);

        setParameterLabelsNames(fieldNamesList);
        initializeRequirementsComboBoxes();
        initializeEntityList();
    }

    private void setParameterLabelsNames(List<String> fieldNamesList) {
        List<JLabel> parameterLabels = new ArrayList<JLabel>() {{
            add(parameterLabel1);
            add(parameterLabel2);
            add(parameterLabel3);
            add(parameterLabel4);
            add(parameterLabel5);
            add(parameterLabel6);
            add(parameterLabel7);
            add(parameterLabel8);
            add(parameterLabel9);
            add(parameterLabel10);
        }};

        for (int i = 0; i < parameterLabels.size(); i++) {
            if (i < fieldNamesList.size()) {
                parameterLabels.get(i).setText(convertToMultiline(fieldNamesList.get(i)));
            }
        }
    }

    private String convertToMultiline(String value) {
        String[] subStrings = value.split(" ");
        String result = "<html>";
        int lastNewLineInjectionPosition = 0;
        for (int i = 0; i < subStrings.length; ++i) {
            result = result + subStrings[i] + " ";
            if (result.length() - lastNewLineInjectionPosition > LABEL_LINE_MAX_LENGTH
                    && i != subStrings.length - 1) {
                result = result + "<br>";
                lastNewLineInjectionPosition = result.length();
            }
        }
        return result;
    }

    private void initializeRequirementsComboBoxes() {
        // order of spell class/subclass mapping is taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=241
        // NOTE: order of this list is HIGHLY important, if you change it, you may
        // accidentally set (via this Editor) i.e "Iceburst" "White Magic - Nature" requirements
        // instead of "Elemental Magic - Ice"
        classSubClassComboBoxContent.put("", Collections.singletonList(""));
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<EntityTuple> createEntityTuples() {
        // inside spell offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=242
        return new ArrayList<EntityTuple>() {{
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
            add(new EntityTuple<>(parameterField1, 32, 4));
            add(new EntityTuple<>(parameterField2, 36, 4));
            add(new EntityTuple<>(parameterField3, 40, 4));
            add(new EntityTuple<>(parameterField4, 44, 4));
            add(new EntityTuple<>(parameterField5, 48, 4));
            add(new EntityTuple<>(parameterField6, 52, 4));
            add(new EntityTuple<>(parameterField7, 56, 4));
            add(new EntityTuple<>(parameterField8, 60, 4));
            add(new EntityTuple<>(parameterField9, 64, 4));
            add(new EntityTuple<>(parameterField10, 68, 4));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Component[] getMainPanelComponents() {
        return mainPanel.getComponents();
    }

    public String getRangeOfPossibleSpellLevels() {
        String result;
        List<Pair<Integer, Long>> abilityLevelOffsets = getAbilityLevelOffsets();
        int size = abilityLevelOffsets.size();
        if (size == 1) {
            result = String.valueOf(abilityLevelOffsets.get(0).getKey());
        } else if (size > 1) {
            result = String.valueOf(abilityLevelOffsets.get(0).getKey());
            result += "-";
            result += String.valueOf(abilityLevelOffsets.get(size - 1).getKey());
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
