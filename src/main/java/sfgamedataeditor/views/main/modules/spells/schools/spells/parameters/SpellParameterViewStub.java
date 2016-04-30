package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.fieldwrapping.Data;
import sfgamedataeditor.fieldwrapping.Disabled;
import sfgamedataeditor.fieldwrapping.Wrapable;
import sfgamedataeditor.listeners.ClassRequirementComboBoxListener;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.IView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpellParameterViewStub implements Wrapable, IView {

    // inside spell offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=242
    private JPanel mainPanel;

    @Disabled
    @Data(offset = 0, length = 2)
    private JTextField numberField;
    private JLabel numberLabel;

    @Disabled
    @Data(offset = 2, length = 2)
    private JTextField typeField;
    private JLabel typeLabel;

    @Data(offset = 4, length = 1)
    private JComboBox<String> requirementClassComboBox;
    private JLabel requirementClassLabel;

    @Data(offset = 5, length = 1)
    private JComboBox<String> requirementSubClassComboBox;
    private JLabel requirementSubClassLabel;

    @Data(offset = 6, length = 1)
    private JTextField requirementLevelField;
    private JLabel requirementLevelLabel;

    @Data(offset = 7, length = 1)
    private JComboBox<String> requirementClassComboBox2;
    private JLabel requirementClassLabel2;

    @Data(offset = 8, length = 1)
    private JComboBox<String> requirementSubClassComboBox2;
    private JLabel requirementSubClassLabel2;

    @Data(offset = 9, length = 1)
    private JTextField requirementLevelField2;
    private JLabel requirementLevelLabel2;

    @Data(offset = 10, length = 1)
    private JComboBox<String> requirementClassComboBox3;
    private JLabel requirementClassLabel3;

    @Data(offset = 11, length = 1)
    private JComboBox<String> requirementSubClassComboBox3;
    private JLabel requirementSubClassLabel3;

    @Data(offset = 12, length = 1)
    private JTextField requirementLevelField3;
    private JLabel requirementLevelLabel3;

    // TODO add possible spell skill requirements (3 bytes offset)

    @Data(offset = 16, length = 2)
    private JTextField manaUsageField;
    private JLabel manaUsageLabel;

    @Data(offset = 18, length = 4)
    private JTextField castTimeField;
    private JLabel castTimeLabel;

    @Data(offset = 22, length = 4)
    private JTextField cooldownField;
    private JLabel cooldownLabel;

    @Data(offset = 26, length = 2)
    private JTextField minRangeField;
    private JLabel minRangeLabel;

    @Data(offset = 28, length = 2)
    private JTextField maxRangeField;
    private JLabel maxRangeLabel;

    @Data(offset = 30, length = 2)
    private JTextField castTypeField;
    private JLabel castTypeLabel;

    @Data(offset = 32, length = 4)
    private JTextField parameterField1;
    private JLabel parameterLabel1;

    @Data(offset = 36, length = 4)
    private JTextField parameterField2;
    private JLabel parameterLabel2;

    @Data(offset = 40, length = 4)
    private JTextField parameterField3;
    private JLabel parameterLabel3;

    @Data(offset = 44, length = 4)
    private JTextField parameterField4;
    private JLabel parameterLabel4;

    @Data(offset = 48, length = 4)
    private JTextField parameterField5;
    private JLabel parameterLabel5;

    @Data(offset = 52, length = 4)
    private JTextField parameterField6;
    private JLabel parameterLabel6;

    @Data(offset = 56, length = 4)
    private JTextField parameterField7;
    private JLabel parameterLabel7;

    @Data(offset = 60, length = 4)
    private JTextField parameterField8;
    private JLabel parameterLabel8;

    @Data(offset = 64, length = 4)
    private JTextField parameterField9;
    private JLabel parameterLabel9;

    private JSeparator parametersSeparator;
    private JLabel parametersLabel;

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
    }};

    public SpellParameterViewStub() {
        parametersLabel.setText(I18N.INSTANCE.getMessage("spellParameters"));
        setCommonLabelsI18nNames();
        initializeRequirementsComboBoxes();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public List<JLabel> getParameterLabels() {
        return parameterLabels;
    }
}
