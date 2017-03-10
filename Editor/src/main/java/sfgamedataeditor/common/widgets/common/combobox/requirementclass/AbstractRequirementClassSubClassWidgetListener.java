package sfgamedataeditor.common.widgets.common.combobox.requirementclass;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.ResourceBundle;

public abstract class AbstractRequirementClassSubClassWidgetListener extends AbstractWidgetListener<RequirementClassSubClassWidget, OffsetableObject> implements ItemListener {

    AbstractRequirementClassSubClassWidgetListener(RequirementClassSubClassWidget widget, Field[] DTOField) {
        super(widget, DTOField);
        fillWidgetClassComboBox();
    }

    protected abstract void fillWidgetClassComboBox();

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
            updateSubClassComboBoxContent((String) selectedSkillClass);
        }

        setWidgetValueToDTOField();
    }

    private void updateSubClassComboBoxContent(String selectedSkillClass) {
        JComboBox<String> comboBox = getWidget().getRequirementSubClassComboBox();
        comboBox.removeAllItems();
        fillSubClassComboBox(selectedSkillClass);
    }

    protected abstract void fillSubClassComboBox(String selectedSkillClass);

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
                break;
            }
        }

        return new int[] {requirementClassValue, requirementSubClassValue};
    }

    @Override
    protected void setFieldValues(final int[] value) {
        final JComboBox<String> requirementClassComboBox = getWidget().getRequirementClassComboBox();
        final JComboBox<String> requirementSubClassComboBox = getWidget().getRequirementSubClassComboBox();
        String skillSchoolKey = ViewTools.getKeyStringByPropertyValue(String.valueOf(value[0]), I18NTypes.SKILL_SCHOOL_MAPPING);
        final String skillSchoolName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, skillSchoolKey);
        String skillSubSchoolName = null;
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.SKILL_SUB_SCHOOL_MAPPING);
        for (String key : bundle.keySet()) {
            if (key.startsWith(skillSchoolKey)) {
                String string = bundle.getString(key);
                if (string.equals(String.valueOf(value[1]))) {
                    skillSubSchoolName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, key);
                    break;
                }
            }
        }

        final String finalSkillSubSchoolName = skillSubSchoolName;
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(requirementClassComboBox) {
            @Override
            protected void setValues() {
                requirementClassComboBox.setSelectedItem(skillSchoolName);
                ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(requirementSubClassComboBox) {
                    @Override
                    protected void setValues() {
                        updateSubClassComboBoxContent((String) requirementClassComboBox.getSelectedItem());
                        requirementSubClassComboBox.setSelectedItem(finalSkillSubSchoolName);
                    }
                });
            }
        });
    }
}