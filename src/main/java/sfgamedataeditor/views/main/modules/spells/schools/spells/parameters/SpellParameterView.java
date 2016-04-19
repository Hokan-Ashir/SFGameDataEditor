package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.dataextraction.DataSavingUtils;
import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.dataextraction.SpellMap;
import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.listeners.ClassRequirementComboBoxListener;
import sfgamedataeditor.listeners.ComboBoxListener;
import sfgamedataeditor.listeners.TextFieldListener;
import sfgamedataeditor.utils.EntityTuple;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.LevelableView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellParameterView extends AbstractView<SpellsView> {

    private static final int LABEL_LINE_MAX_LENGTH = 12;

    private SpellParameterEventParameter parameter;

    private JPanel mainPanel;
    private JTextField numberField;
    private JLabel numberLabel;
    private JLabel typeLabel;
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

    // TODO get rid of this as private variable
    private LevelableView<SpellsView> view = (LevelableView<SpellsView>) ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, getParentView()));
    ;
    private List<EntityTuple> entities = new ArrayList<>();
    private List<JLabel> parameterLabels = new ArrayList<JLabel>() {{
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

    private Map<JTextField, TextFieldListener> textListeners = new HashMap<>();
    private Map<JComboBox, ComboBoxListener> comboBoxListeners = new HashMap<>();

    public SpellParameterView(SpellsView parentView) {
        super(parentView);
        initializeRequirementsComboBoxes();
        view.getLevelComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                // TODO get rid of multiple new event instances
                ClassTuple tuple = new ClassTuple(SpellParameterView.class, getParentView());
                ShowSpellParameterViewEvent event = new ShowSpellParameterViewEvent(tuple);
                SpellParameterEventParameter eventParameter = new SpellParameterEventParameter(parameter.getSpellId(), view.getSelectedLevel());
                event.setObjectParameter(eventParameter);
                EventHandlerRegister.INSTANCE.fireEvent(event);
            }
        });
        setCommonLabelsI18nNames();
        attachFieldListeners();
    }

    private void setCommonLabelsI18nNames() {
        numberLabel.setText(I18N.INSTANCE.getMessage("spellNumber"));
        typeLabel.setText(I18N.INSTANCE.getMessage("spellType"));
        requirementClassLabel.setText(I18N.INSTANCE.getMessage("spellRequirementClass"));
        requirementSubClassLabel.setText(I18N.INSTANCE.getMessage("spellRequirementSubClass"));
        requirementLevelLabel.setText(I18N.INSTANCE.getMessage("spellRequirementLevel"));
        requirementClassLabel2.setText(I18N.INSTANCE.getMessage("spellRequirementClass"));
        requirementSubClassLabel2.setText(I18N.INSTANCE.getMessage("spellRequirementSubClass"));
        requirementLevelLabel2.setText(I18N.INSTANCE.getMessage("spellRequirementLevel"));
        requirementClassLabel3.setText(I18N.INSTANCE.getMessage("spellRequirementClass"));
        requirementSubClassLabel3.setText(I18N.INSTANCE.getMessage("spellRequirementSubClass"));
        requirementLevelLabel3.setText(I18N.INSTANCE.getMessage("spellRequirementLevel"));
        manaUsageLabel.setText(I18N.INSTANCE.getMessage("spellManaUsage"));
        castTimeLabel.setText(I18N.INSTANCE.getMessage("spellCastTime"));
        cooldownLabel.setText(I18N.INSTANCE.getMessage("spellCooldown"));
        minRangeLabel.setText(I18N.INSTANCE.getMessage("spellMinRange"));
        maxRangeLabel.setText(I18N.INSTANCE.getMessage("spellMaxRange"));
        castTypeLabel.setText(I18N.INSTANCE.getMessage("spellCastType"));
    }

    private void attachFieldListeners() {
        List<JComponent> components = new ArrayList<JComponent>() {{
            add(numberField);
            add(typeField);
            add(requirementClassComboBox);
            add(requirementSubClassComboBox);
            add(requirementLevelField);
            add(requirementClassComboBox2);
            add(requirementSubClassComboBox2);
            add(requirementLevelField2);
            add(requirementClassComboBox3);
            add(requirementSubClassComboBox3);
            add(requirementLevelField3);
            add(manaUsageField);
            add(castTimeField);
            add(cooldownField);
            add(minRangeField);
            add(maxRangeField);
            add(castTypeField);
            add(parameterField1);
            add(parameterField2);
            add(parameterField3);
            add(parameterField4);
            add(parameterField5);
            add(parameterField6);
            add(parameterField7);
            add(parameterField8);
            add(parameterField9);
            add(parameterField10);
        }};

        for (JComponent component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                TextFieldListener listener = new TextFieldListener(textField);
                textField.getDocument().addDocumentListener(listener);
                textListeners.put(textField, listener);
            } else if (component instanceof JComboBox) {
                JComboBox comboBox = (JComboBox) component;
                ComboBoxListener listener = new ComboBoxListener(comboBox);
                comboBox.addItemListener(listener);
                comboBoxListeners.put(comboBox, listener);
            }
        }
    }

    private void initializeRequirementsComboBoxes() {
        Map<String, List<String>> map = Mappings.INSTANCE.CLASS_SUBCLASS_COMBOBOX_MAP;
        for (String s : map.keySet()) {
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

    private void mapEntities(long blockOffset) {
        // inside spell offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=242
        entities.add(new EntityTuple<>(numberField, blockOffset, 2));
        entities.add(new EntityTuple<>(typeField, blockOffset + 2, 2));
        entities.add(new EntityTuple<>(requirementClassComboBox, blockOffset + 4, 1));
        entities.add(new EntityTuple<>(requirementSubClassComboBox, blockOffset + 5, 1));
        entities.add(new EntityTuple<>(requirementLevelField, blockOffset + 6, 1));
        entities.add(new EntityTuple<>(requirementClassComboBox2, blockOffset + 7, 1));
        entities.add(new EntityTuple<>(requirementSubClassComboBox2, blockOffset + 8, 1));
        entities.add(new EntityTuple<>(requirementLevelField2, blockOffset + 9, 1));
        entities.add(new EntityTuple<>(requirementClassComboBox3, blockOffset + 10, 1));
        entities.add(new EntityTuple<>(requirementSubClassComboBox3, blockOffset + 11, 1));
        entities.add(new EntityTuple<>(requirementLevelField3, blockOffset + 12, 1));
        // TODO add possible spell skill requirements (3 bytes offset)
        entities.add(new EntityTuple<>(manaUsageField, blockOffset + 16, 2));
        entities.add(new EntityTuple<>(castTimeField, blockOffset + 18, 4));
        entities.add(new EntityTuple<>(cooldownField, blockOffset + 22, 4));
        entities.add(new EntityTuple<>(minRangeField, blockOffset + 26, 2));
        entities.add(new EntityTuple<>(maxRangeField, blockOffset + 28, 2));
        entities.add(new EntityTuple<>(castTypeField, blockOffset + 30, 2));
        entities.add(new EntityTuple<>(parameterField1, blockOffset + 32, 4));
        entities.add(new EntityTuple<>(parameterField2, blockOffset + 36, 4));
        entities.add(new EntityTuple<>(parameterField3, blockOffset + 40, 4));
        entities.add(new EntityTuple<>(parameterField4, blockOffset + 44, 4));
        entities.add(new EntityTuple<>(parameterField5, blockOffset + 48, 4));
        entities.add(new EntityTuple<>(parameterField6, blockOffset + 52, 4));
        entities.add(new EntityTuple<>(parameterField7, blockOffset + 56, 4));
        entities.add(new EntityTuple<>(parameterField8, blockOffset + 60, 4));
        entities.add(new EntityTuple<>(parameterField9, blockOffset + 64, 4));
        entities.add(new EntityTuple<>(parameterField10, blockOffset + 68, 4));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateData(Object data) {
        if (data != null) {
            // TODO get rid of class casting
            parameter = (SpellParameterEventParameter) data;
        }

        int selectedSpellId = parameter.getSpellId();
        int selectedLevel = parameter.getSpellLevel();
        List<Pair<Integer, Long>> spellLevelToOffsetList = OffsetProvider.INSTANCE.getSpellOffsets().get(selectedSpellId);
        setSpellAvaliableLevels(spellLevelToOffsetList, selectedLevel);
        selectedLevel = updateSelectedLevel(selectedLevel);
        long spellOffset = getSpellParameterOffset(selectedSpellId, selectedLevel);
        setFieldsData(spellOffset);
        setSpellParameterLabelNames();
    }

    // in case user selected spell with level-range [1; 12] with level 5
    // then selected spell with level range [13; 20]
    // we have to select level 13 to appropriately load correct data
    private int updateSelectedLevel(int selectedLevel) {
        JComboBox<String> comboBox = view.getLevelComboBox();
        int minimumSpellLevel = Integer.valueOf(comboBox.getItemAt(0));
        int maximumSpellLevel = Integer.valueOf(comboBox.getItemAt(comboBox.getItemCount() - 1));


        if (selectedLevel <= maximumSpellLevel && selectedLevel >= minimumSpellLevel) {
            return selectedLevel;
        } else {
            return minimumSpellLevel;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void setSpellAvaliableLevels(List<Pair<Integer, Long>> spellLevelToOffsetList, int selectedLevel) {
        JComboBox<String> comboBox = view.getLevelComboBox();
        ItemListener[] listeners = comboBox.getItemListeners();
        for (ItemListener listener : listeners) {
            comboBox.removeItemListener(listener);
        }

        comboBox.removeAllItems();
        for (Pair<Integer, Long> integerLongPair : spellLevelToOffsetList) {
            comboBox.addItem(String.valueOf(integerLongPair.getKey()));
        }

        // TODO split this methods
        setLevelComboBoxItem(selectedLevel);

        for (ItemListener listener : listeners) {
            comboBox.addItemListener(listener);
        }
    }

    private void setLevelComboBoxItem(int spellLevel) {
        JComboBox<String> comboBox = view.getLevelComboBox();
        int minimumSpellLevel = Integer.valueOf(comboBox.getItemAt(0));
        int maximumSpellLevel = Integer.valueOf(comboBox.getItemAt(comboBox.getItemCount() - 1));

        // case when user selected i.e Death-3, then selected
        // Cannibalize, game doesn't have Cannibalize-3, cause
        // its Shadow of Phoenix spell and has level range [13; 20]
        // if this happens, we select first level from combobox
        // same work for spells that have spell range [1; 12]
        if (spellLevel <= maximumSpellLevel && spellLevel >= minimumSpellLevel) {
            if (spellLevel > (maximumSpellLevel - minimumSpellLevel)) {
                spellLevel %= minimumSpellLevel;
            } else {
                spellLevel--;
            }
            comboBox.setSelectedIndex(spellLevel);
        } else {
            comboBox.setSelectedIndex(0);
        }
    }

    private void setSpellParameterLabelNames() {
        String selectedSpellName = (String) getParentView().getSelectedModuleValue();
        for (Map.Entry<Integer, Pair<String, List<String>>> integerPairEntry : SpellMap.INSTANCE.getSpellMap().entrySet()) {
            String spellName = integerPairEntry.getValue().getKey();
            if (selectedSpellName.equals(spellName)) {
                List<String> spellParameterNames = integerPairEntry.getValue().getValue();
                for (int i = 0; i < parameterLabels.size(); i++) {
                    if (i < spellParameterNames.size()) {
                        parameterLabels.get(i).setText(convertToMultiline(spellParameterNames.get(i)));
                    } else {
                        parameterLabels.get(i).setText(I18N.INSTANCE.getMessage("spellParameterNotUsed"));
                    }
                }
                break;
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

    private void setFieldsData(long skillParametersOffset) {
        mapEntities(skillParametersOffset);
        for (EntityTuple entity : entities) {
            int value = DataSavingUtils.loadDataFromFile(entity.getOffsetInBytes(), entity.getLengthInBytes());
            if (entity.getComponent() instanceof JTextField) {
                JTextField textField = (JTextField) entity.getComponent();
                TextFieldListener listener = textListeners.get(textField);
                textField.getDocument().removeDocumentListener(listener);
                textField.setText(String.valueOf(value));
                textField.getDocument().addDocumentListener(listener);
                listener.setOffset(entity.getOffsetInBytes());
            } else if (entity.getComponent() instanceof JComboBox) {
                JComboBox comboBox = (JComboBox) entity.getComponent();
                ComboBoxListener listener = comboBoxListeners.get(comboBox);
                comboBox.removeItemListener(listener);
                comboBox.setSelectedItem(comboBox.getItemAt(value));
                comboBox.addItemListener(listener);
                listener.setOffset(entity.getOffsetInBytes());
            }
        }
    }

    private long getSpellParameterOffset(int selectedSpellId, int selectedLevel) {
        long skillParametersOffset = 0L;
        Map<Integer, List<Pair<Integer, Long>>> spellOffsets = OffsetProvider.INSTANCE.getSpellOffsets();
        for (Pair<Integer, Long> integerLongPair : spellOffsets.get(selectedSpellId)) {
            if (integerLongPair.getKey() == selectedLevel) {
                skillParametersOffset = integerLongPair.getValue();
                break;
            }
        }

        return skillParametersOffset;
    }
}
