package sfgamedataeditor.common.widgets.items.weapons.type;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;

public class WeaponTypeWidgetListener extends AbstractWidgetListener<WeaponTypeWidget, OffsetableObject> implements ItemListener {

    public WeaponTypeWidgetListener(WeaponTypeWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        String selectedItem = (String) getWidget().getTypeComboBox().getSelectedItem();
        Integer selectedWeaponType = getWidget().getWeaponTypeByName(selectedItem);
        // type consists of two bytes, one of which is 0 by default (see corresponding widget class)
        return new int[] {selectedWeaponType, 0};
    }

    @Override
    protected void setFieldValues(int[] value) {
        String weaponTypeById = getWidget().getWeaponTypeById(value[0]);
        getWidget().getTypeComboBox().setSelectedItem(weaponTypeById);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        String selectedItem = (String) getWidget().getTypeComboBox().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        setWidgetValueToDTOField();
    }
}
