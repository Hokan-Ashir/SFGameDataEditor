package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.fieldwrapping.*;
import sfgamedataeditor.listeners.ClassRequirementComboBoxListener;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.IView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@MappedClass(mappedClass = SpellParameters.class)
public class SpellParameterViewStub implements Wrapable, IView {

    // inside spell offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=242
    private JPanel mainPanel;

    @Disabled
    @MappedColumn(name = "spellNumber")
    private JTextField numberField;
    private JLabel numberLabel;

    @Disabled
    @MappedColumn(name = "spellNameId")
    private JTextField typeField;
    private JLabel typeLabel;

    @MappedColumn(name = "requirementClass1")
    private JComboBox<String> requirementClassComboBox;
    private JLabel requirementClassLabel;

    @MappedColumn(name = "requirementSubClass1")
    private JComboBox<String> requirementSubClassComboBox;
    private JLabel requirementSubClassLabel;

    @MappedColumn(name = "requirementLevel1")
    private JTextField requirementLevelField;
    private JLabel requirementLevelLabel;

    @MappedColumn(name = "requirementClass2")
    private JComboBox<String> requirementClassComboBox2;
    private JLabel requirementClassLabel2;

    @MappedColumn(name = "requirementSubClass2")
    private JComboBox<String> requirementSubClassComboBox2;
    private JLabel requirementSubClassLabel2;

    @MappedColumn(name = "requirementLevel2")
    private JTextField requirementLevelField2;
    private JLabel requirementLevelLabel2;

    @MappedColumn(name = "requirementClass3")
    private JComboBox<String> requirementClassComboBox3;
    private JLabel requirementClassLabel3;

    @MappedColumn(name = "requirementSubClass3")
    private JComboBox<String> requirementSubClassComboBox3;
    private JLabel requirementSubClassLabel3;

    @MappedColumn(name = "requirementLevel3")
    private JTextField requirementLevelField3;
    private JLabel requirementLevelLabel3;

    // TODO add possible spell skill requirements (3 bytes offset)

    @MappedColumn(name = "manaUsage")
    private JTextField manaUsageField;
    private JLabel manaUsageLabel;

    @MappedColumn(name = "castTime")
    private JTextField castTimeField;
    private JLabel castTimeLabel;

    @MappedColumn(name = "cooldown")
    private JTextField cooldownField;
    private JLabel cooldownLabel;

    @MappedColumn(name = "minRange")
    private JTextField minRangeField;
    private JLabel minRangeLabel;

    @MappedColumn(name = "maxRange")
    private JTextField maxRangeField;
    private JLabel maxRangeLabel;

    @MappedColumn(name = "castType")
    private JTextField castTypeField;
    private JLabel castTypeLabel;

    @MappedColumn(name = "parameter1")
    private JTextField parameterField1;
    private JLabel parameterLabel1;

    @MappedColumn(name = "parameter2")
    private JTextField parameterField2;
    private JLabel parameterLabel2;

    @MappedColumn(name = "parameter3")
    private JTextField parameterField3;
    private JLabel parameterLabel3;

    @MappedColumn(name = "parameter4")
    private JTextField parameterField4;
    private JLabel parameterLabel4;

    @MappedColumn(name = "parameter5")
    private JTextField parameterField5;
    private JLabel parameterLabel5;

    @MappedColumn(name = "parameter6")
    private JTextField parameterField6;
    private JLabel parameterLabel6;

    @MappedColumn(name = "parameter7")
    private JTextField parameterField7;
    private JLabel parameterLabel7;

    @MappedColumn(name = "parameter8")
    private JTextField parameterField8;
    private JLabel parameterLabel8;

    @MappedColumn(name = "parameter9")
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
