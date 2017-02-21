package sfgamedataeditor.common.widgets.common.combobox.requirementclass;

import i18nbase.objects.I18NObject;
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
        List<? extends I18NObject> skillSchoolObjects = I18NService.INSTANCE.getI18NObjects(I18NTypes.SKILL_SCHOOL_MAPPING);
        for (I18NObject skillSchoolObject : skillSchoolObjects) {
            if (I18NService.INSTANCE.getMessage(I18NTypes.COMMON, skillSchoolObject.key).equals(requirementClassComboBox.getSelectedItem())) {
                requirementClassValue = Integer.parseInt(skillSchoolObject.value);
                break;
            }
        }

        if (requirementClassValue == 0) {
            return new int[] {0, 0};
        }

        JComboBox<String> requirementSubClassComboBox = getWidget().getRequirementSubClassComboBox();
        int requirementSubClassValue = 0;
        List<? extends I18NObject> skillSubSchoolObjects = I18NService.INSTANCE.getI18NObjects(I18NTypes.SKILL_SUB_SCHOOL_MAPPING);
        for (I18NObject skillSubSchoolObject : skillSubSchoolObjects) {
            if (I18NService.INSTANCE.getMessage(I18NTypes.COMMON, skillSubSchoolObject.key).equals(requirementSubClassComboBox.getSelectedItem())) {
                requirementSubClassValue = Integer.parseInt(skillSubSchoolObject.value);
                requirementSubClassValue %= requirementClassValue * 10;
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