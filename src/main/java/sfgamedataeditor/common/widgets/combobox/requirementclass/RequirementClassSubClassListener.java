package sfgamedataeditor.common.widgets.combobox.requirementclass;

import sfgamedataeditor.database.objects.OffsetableObject;
import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.fieldwrapping.AbstractFieldListener;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;

public class RequirementClassSubClassListener extends AbstractFieldListener<RequirementClassSubClassWidget, OffsetableObject> implements ItemListener {


    public RequirementClassSubClassListener(RequirementClassSubClassWidget widget, Field[] DTOField) {
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

        List<String> subClasses = Mappings.INSTANCE.CLASS_SUBCLASS_COMBOBOX_MAP.get(e.getItem());
        JComboBox<String> comboBox = getWidget().getRequirementSubClassComboBox();
        comboBox.removeAllItems();
        for (String subClass : subClasses) {
            comboBox.addItem(subClass);
        }
    }

    @Override
    protected int[] getFieldValues() {
        // TODO awkward, should be changed
        return null;
    }

    @Override
    protected void setFieldValues(final int[] value) {
        final JComboBox<String> requirementClassComboBox = getWidget().getRequirementClassComboBox();
        requirementClassComboBox.setSelectedItem(requirementClassComboBox.getItemAt(value[0]));

        final JComboBox<String> requirementSubClassComboBox = getWidget().getRequirementSubClassComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(requirementSubClassComboBox) {
            @Override
            protected void setValues() {
                requirementSubClassComboBox.setSelectedItem(requirementSubClassComboBox.getItemAt(value[1]));
            }
        });
    }
}