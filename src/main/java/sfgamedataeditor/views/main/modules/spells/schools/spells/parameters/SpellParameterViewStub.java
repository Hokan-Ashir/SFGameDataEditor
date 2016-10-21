package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.database.objects.SpellName;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.fieldwrapping.Disabled;
import sfgamedataeditor.fieldwrapping.MappedColumn;
import sfgamedataeditor.fieldwrapping.Wrapable;
import sfgamedataeditor.listeners.ClassRequirementComboBoxListener;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.IView;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class SpellParameterViewStub implements Wrapable, IView {

    // inside spell offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=14&t=242
    private JPanel mainPanel;

    @Disabled
    @MappedColumn(name = "spellNumber", daoClass = SpellParameters.class)
    private JTextField numberField;
    private JLabel numberLabel;

    @Disabled
    @MappedColumn(name = "spellNameId", daoClass = SpellParameters.class)
    private JTextField typeField;
    private JLabel typeLabel;

    @MappedColumn(name = "requirementClass1", daoClass = SpellParameters.class)
    private JComboBox<String> requirementClassComboBox;
    private JLabel requirementClassLabel;

    @MappedColumn(name = "requirementSubClass1", daoClass = SpellParameters.class)
    private JComboBox<String> requirementSubClassComboBox;
    private JLabel requirementSubClassLabel;

    @MappedColumn(name = "requirementLevel1", daoClass = SpellParameters.class)
    private JTextField requirementLevelField;
    private JLabel requirementLevelLabel;

    @MappedColumn(name = "requirementClass2", daoClass = SpellParameters.class)
    private JComboBox<String> requirementClassComboBox2;
    private JLabel requirementClassLabel2;

    @MappedColumn(name = "requirementSubClass2", daoClass = SpellParameters.class)
    private JComboBox<String> requirementSubClassComboBox2;
    private JLabel requirementSubClassLabel2;

    @MappedColumn(name = "requirementLevel2", daoClass = SpellParameters.class)
    private JTextField requirementLevelField2;
    private JLabel requirementLevelLabel2;

    @MappedColumn(name = "requirementClass3", daoClass = SpellParameters.class)
    private JComboBox<String> requirementClassComboBox3;
    private JLabel requirementClassLabel3;

    @MappedColumn(name = "requirementSubClass3", daoClass = SpellParameters.class)
    private JComboBox<String> requirementSubClassComboBox3;
    private JLabel requirementSubClassLabel3;

    @MappedColumn(name = "requirementLevel3", daoClass = SpellParameters.class)
    private JTextField requirementLevelField3;
    private JLabel requirementLevelLabel3;

    // TODO add possible spell skill requirements (3 bytes offset)

    @MappedColumn(name = "manaUsage", daoClass = SpellParameters.class)
    private JTextField manaUsageField;
    private JLabel manaUsageLabel;

    @MappedColumn(name = "castTime", daoClass = SpellParameters.class)
    private JTextField castTimeField;
    private JLabel castTimeLabel;

    @MappedColumn(name = "cooldown", daoClass = SpellParameters.class)
    private JTextField cooldownField;
    private JLabel cooldownLabel;

    @MappedColumn(name = "minRange", daoClass = SpellParameters.class)
    private JTextField minRangeField;
    private JLabel minRangeLabel;

    @MappedColumn(name = "maxRange", daoClass = SpellParameters.class)
    private JTextField maxRangeField;
    private JLabel maxRangeLabel;

    @MappedColumn(name = "castType", daoClass = SpellParameters.class)
    private JTextField castTypeField;
    private JLabel castTypeLabel;

    @MappedColumn(name = "parameter1", daoClass = SpellParameters.class)
    private JTextField parameterField1;
    @MappedColumn(name = "field1", daoClass = SpellName.class)
    private JLabel parameterLabel1;

    @MappedColumn(name = "parameter2", daoClass = SpellParameters.class)
    private JTextField parameterField2;
    @MappedColumn(name = "field2", daoClass = SpellName.class)
    private JLabel parameterLabel2;

    @MappedColumn(name = "parameter3", daoClass = SpellParameters.class)
    private JTextField parameterField3;
    @MappedColumn(name = "field3", daoClass = SpellName.class)
    private JLabel parameterLabel3;

    @MappedColumn(name = "parameter4", daoClass = SpellParameters.class)
    private JTextField parameterField4;
    @MappedColumn(name = "field4", daoClass = SpellName.class)
    private JLabel parameterLabel4;

    @MappedColumn(name = "parameter5", daoClass = SpellParameters.class)
    private JTextField parameterField5;
    @MappedColumn(name = "field5", daoClass = SpellName.class)
    private JLabel parameterLabel5;

    @MappedColumn(name = "parameter6", daoClass = SpellParameters.class)
    private JTextField parameterField6;
    @MappedColumn(name = "field6", daoClass = SpellName.class)
    private JLabel parameterLabel6;

    @MappedColumn(name = "parameter7", daoClass = SpellParameters.class)
    private JTextField parameterField7;
    @MappedColumn(name = "field7", daoClass = SpellName.class)
    private JLabel parameterLabel7;

    @MappedColumn(name = "parameter8", daoClass = SpellParameters.class)
    private JTextField parameterField8;
    @MappedColumn(name = "field8", daoClass = SpellName.class)
    private JLabel parameterLabel8;

    @MappedColumn(name = "parameter9", daoClass = SpellParameters.class)
    private JTextField parameterField9;
    @MappedColumn(name = "field9", daoClass = SpellName.class)
    private JLabel parameterLabel9;

    private JSeparator parametersSeparator;
    private JLabel parametersLabel;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getWrappableClass() {
        return SpellParameters.class;
    }
}
