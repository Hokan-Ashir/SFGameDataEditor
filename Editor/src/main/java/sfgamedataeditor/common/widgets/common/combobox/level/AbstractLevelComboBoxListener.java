package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;

// TODO generify all children
public abstract class AbstractLevelComboBoxListener extends AbstractWidgetListener<LevelComboBoxWidget, LevelComboBoxParameter> implements ItemListener {

    public AbstractLevelComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
        super(widget, mappedField);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        String selectedItem = (String) getWidget().getComboBox().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        processSelectedItemEvent(selectedItem);
    }

    protected abstract void processSelectedItemEvent(String selectedItem);

    @Override
    public void updateWidgetValue(final LevelComboBoxParameter mappedObject) {
        final JComboBox<String> comboBox = getWidget().getComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
            @Override
            protected void setValues() {
                comboBox.removeAllItems();
                for (Integer level : mappedObject.getLevels()) {
                    comboBox.addItem(String.valueOf(level));
                }

                comboBox.setSelectedItem(String.valueOf(mappedObject.getSelectedLevel()));
            }
        });
    }

    @Override
    protected int[] getFieldValues() {
        // stub, cause no data should be extracted from widget's data
        return null;
    }

    @Override
    protected void setFieldValues(final int[] value) {
        final JComboBox<String> comboBox = getWidget().getComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
            @Override
            protected void setValues() {
                comboBox.setSelectedItem(comboBox.getItemAt(value[0]));
            }
        });
    }
}
