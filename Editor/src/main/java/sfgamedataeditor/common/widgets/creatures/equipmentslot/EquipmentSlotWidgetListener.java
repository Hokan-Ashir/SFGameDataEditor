package sfgamedataeditor.common.widgets.creatures.equipmentslot;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EquipmentSlotWidgetListener extends AbstractWidgetListener<EquipmentSlotWidget, OffsetableObject> implements ItemListener {

    private final Map<JRadioButton, Integer> widgetButtonsMap = new HashMap<>();

    public EquipmentSlotWidgetListener(EquipmentSlotWidget component, Field... mappedFields) {
        super(component, mappedFields);
        initializeWidgetButtonsMapping();
    }

    private void initializeWidgetButtonsMapping() {
        //    01 - all slots available
        //    02 - only hands and rings available
        //    03 - no slots available
        widgetButtonsMap.put(getWidget().getAllSlotsAvailableRadioButton(), 1);
        widgetButtonsMap.put(getWidget().getHandsAndRingsAvailableRadioButton(), 2);
        widgetButtonsMap.put(getWidget().getNoSlotsAvailable(), 3);
    }

    @Override
    protected int[] getFieldValues() {
        int value = 0;
        for (Map.Entry<JRadioButton, Integer> entry : widgetButtonsMap.entrySet()) {
            JRadioButton radioButton = entry.getKey();
            if (radioButton.isSelected()) {
                value = entry.getValue();
                break;
            }
        }

        return new int[]{value};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int vulnerabilityValue = value[0];

        for (Map.Entry<JRadioButton, Integer> entry : widgetButtonsMap.entrySet()) {
            if (entry.getValue().equals(vulnerabilityValue)) {
                entry.getKey().setSelected(true);
                break;
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            return;
        }

        Object source = e.getSource();
        for (Map.Entry<JRadioButton, Integer> entry : widgetButtonsMap.entrySet()) {
            JRadioButton radioButton = entry.getKey();
            if (!radioButton.equals(source)) {
                radioButton.setSelected(false);
            }
        }

        setWidgetValueToDTOField();
    }
}
