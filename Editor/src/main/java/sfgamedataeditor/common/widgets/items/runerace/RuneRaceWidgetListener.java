package sfgamedataeditor.common.widgets.items.runerace;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;

public class RuneRaceWidgetListener extends AbstractWidgetListener<RuneRaceWidget, OffsetableObject> implements ItemListener {

    public RuneRaceWidgetListener(RuneRaceWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        String selectedItem = (String) getWidget().getRaceNameComboBox().getSelectedItem();
        Integer selectedRuneRace = getWidget().getRaceIdByByName(selectedItem);
        return new int[] {selectedRuneRace};
    }

    @Override
    protected void setFieldValues(int[] value) {
        String runeRaceNameByType = getWidget().getRaceNameById(value[0]);
        getWidget().getRaceNameComboBox().setSelectedItem(runeRaceNameByType);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        String selectedItem = (String) getWidget().getRaceNameComboBox().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        setWidgetValueToDTOField();
    }
}
