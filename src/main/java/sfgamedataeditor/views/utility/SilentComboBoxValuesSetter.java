package sfgamedataeditor.views.utility;

import javax.swing.*;
import java.awt.event.ItemListener;

public abstract class SilentComboBoxValuesSetter<T> {

    private JComboBox<T> comboBox;

    protected SilentComboBoxValuesSetter(JComboBox<T> comboBox) {
        this.comboBox = comboBox;
    }

    public void setValuesSilently() {
        ItemListener[] listeners = comboBox.getItemListeners();
        for (ItemListener listener : listeners) {
            comboBox.removeItemListener(listener);
        }

        setValues();

        for (ItemListener listener : listeners) {
            comboBox.addItemListener(listener);
        }
    }

    protected abstract void setValues();
}
