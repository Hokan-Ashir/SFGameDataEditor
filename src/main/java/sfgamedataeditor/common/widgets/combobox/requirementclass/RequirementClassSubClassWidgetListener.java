package sfgamedataeditor.common.widgets.combobox.requirementclass;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.objects.OffsetableObject;
import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class RequirementClassSubClassWidgetListener extends AbstractWidgetListener<RequirementClassSubClassWidget, OffsetableObject> implements ItemListener {

    private static final String SKILL_SUB_SCHOOL_MAPPING_FILENAME = "skillSubSchoolMapping";
    private static final String SKILL_SCHOOL_MAPPING_FILENAME = "skillSchoolMapping";
    private static final ResourceBundle SUBSCHOOL_BUNDLE = ResourceBundle.getBundle(SKILL_SUB_SCHOOL_MAPPING_FILENAME, Locale.getDefault());
    private static final ResourceBundle SCHOOL_BUNDLE = ResourceBundle.getBundle(SKILL_SCHOOL_MAPPING_FILENAME, Locale.getDefault());

    public RequirementClassSubClassWidgetListener(RequirementClassSubClassWidget widget, Field[] DTOField) {
        super(widget, DTOField);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        if (e.getSource().equals(getWidget().getRequirementClassComboBox())) {
            Object selectedSkillClass = e.getItem();
            updateSubClassComboBoxContent(selectedSkillClass);
        }

        setWidgetValueToDTOField();
    }

    private void updateSubClassComboBoxContent(Object selectedSkillClass) {
        List<String> subClasses = Mappings.INSTANCE.CLASS_SUBCLASS_COMBOBOX_MAP.get(selectedSkillClass);
        JComboBox<String> comboBox = getWidget().getRequirementSubClassComboBox();
        comboBox.removeAllItems();
        for (String subClass : subClasses) {
            comboBox.addItem(subClass);
        }
    }

    @Override
    protected int[] getFieldValues() {
        JComboBox<String> requirementClassComboBox = getWidget().getRequirementClassComboBox();
        int requirementClassValue = 0;
        for (String key : SCHOOL_BUNDLE.keySet()) {
            if (I18N.INSTANCE.getMessage(key).equals(requirementClassComboBox.getSelectedItem())) {
                requirementClassValue = Integer.parseInt(SCHOOL_BUNDLE.getString(key));
                break;
            }
        }

        if (requirementClassValue == 0) {
            return new int[] {0, 0};
        }

        JComboBox<String> requirementSubClassComboBox = getWidget().getRequirementSubClassComboBox();
        int requirementSubClassValue = 0;
        for (String key : SUBSCHOOL_BUNDLE.keySet()) {
            if (I18N.INSTANCE.getMessage(key).equals(requirementSubClassComboBox.getSelectedItem())) {
                requirementSubClassValue = Integer.parseInt(SUBSCHOOL_BUNDLE.getString(key));
                requirementSubClassValue /= requirementClassValue * 10;
                break;
            }
        }

        return new int[] {requirementClassValue, requirementSubClassValue};
    }

    @Override
    protected void setFieldValues(final int[] value) {
        final JComboBox<String> requirementClassComboBox = getWidget().getRequirementClassComboBox();
        final JComboBox<String> requirementSubClassComboBox = getWidget().getRequirementSubClassComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(requirementClassComboBox) {
            @Override
            protected void setValues() {
                requirementClassComboBox.setSelectedItem(requirementClassComboBox.getItemAt(value[0]));
                ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(requirementSubClassComboBox) {
                    @Override
                    protected void setValues() {
                        updateSubClassComboBoxContent(requirementClassComboBox.getSelectedItem());
                        requirementSubClassComboBox.setSelectedItem(requirementSubClassComboBox.getItemAt(value[1]));
                    }
                });
            }
        });
    }
}