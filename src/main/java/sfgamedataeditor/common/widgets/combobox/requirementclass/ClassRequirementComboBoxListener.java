package sfgamedataeditor.common.widgets.combobox.requirementclass;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.fieldwrapping.AbstractFieldListener;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;

public class ClassRequirementComboBoxListener extends AbstractFieldListener<JComboBox> implements ItemListener {


    public ClassRequirementComboBoxListener(JComboBox component, Field DTOField) {
        super(component, DTOField);
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
        getComponent().removeAllItems();
        for (String subClass : subClasses) {
            getComponent().addItem(subClass);
        }
    }

    @Override
    protected int getFieldValue() {
        return 0;
    }

    @Override
    protected void setFieldValue(int value) {
    }
}