package sfgamedataeditor.common.widgets.spells.auratarget;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;

public class AuraTargetWidgetListener extends AbstractWidgetListener<AuraTargetWidget, OffsetableObject> implements ItemListener {

    public AuraTargetWidgetListener(AuraTargetWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        int value = getWidget().getAuraTargetComboBoxValue();
        return new int[]{value};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int auraTargetType = value[0];
        getWidget().setAuraTargetComboBoxValue(auraTargetType);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        String selectedItem = (String) getWidget().getAuraTargetComboBox().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        setWidgetValueToDTOField();
    }
}
