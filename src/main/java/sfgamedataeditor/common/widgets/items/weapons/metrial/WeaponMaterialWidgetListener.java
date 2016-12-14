package sfgamedataeditor.common.widgets.items.weapons.metrial;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;

public class WeaponMaterialWidgetListener extends AbstractWidgetListener<WeaponMaterialWidget, OffsetableObject> implements ItemListener {

    public WeaponMaterialWidgetListener(WeaponMaterialWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        String selectedItem = (String) getWidget().getMaterialComboBox().getSelectedItem();
        Integer selectedWeaponMaterial = getWidget().getWeaponMaterialByName(selectedItem);
        // material consists of two bytes, one of which is 0 by default (see corresponding widget class)
        return new int[] {selectedWeaponMaterial, 0};
    }

    @Override
    protected void setFieldValues(int[] value) {
        String weaponMaterialByType = getWidget().getWeaponMaterialById(value[0]);
        getWidget().getMaterialComboBox().setSelectedItem(weaponMaterialByType);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        String selectedItem = (String) getWidget().getMaterialComboBox().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        setWidgetValueToDTOField();
    }
}
