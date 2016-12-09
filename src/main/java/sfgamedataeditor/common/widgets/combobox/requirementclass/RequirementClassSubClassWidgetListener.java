package sfgamedataeditor.common.widgets.combobox.requirementclass;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ResourceBundle;

public class RequirementClassSubClassWidgetListener extends AbstractWidgetListener<RequirementClassSubClassWidget, OffsetableObject> implements ItemListener {

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
        ResourceBundle schoolBundle = I18NService.INSTANCE.getBundle(I18NTypes.SKILL_SCHOOL_MAPPING);
        for (String key : schoolBundle.keySet()) {
            if (I18NService.INSTANCE.getMessage(I18NTypes.COMMON, key).equals(requirementClassComboBox.getSelectedItem())) {
                requirementClassValue = Integer.parseInt(schoolBundle.getString(key));
                break;
            }
        }

        if (requirementClassValue == 0) {
            return new int[] {0, 0};
        }

        JComboBox<String> requirementSubClassComboBox = getWidget().getRequirementSubClassComboBox();
        int requirementSubClassValue = 0;
        ResourceBundle subSchoolBundle = I18NService.INSTANCE.getBundle(I18NTypes.SKILL_SUB_SCHOOL_MAPPING);
        for (String key : subSchoolBundle.keySet()) {
            if (I18NService.INSTANCE.getMessage(I18NTypes.COMMON, key).equals(requirementSubClassComboBox.getSelectedItem())) {
                requirementSubClassValue = Integer.parseInt(subSchoolBundle.getString(key));
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