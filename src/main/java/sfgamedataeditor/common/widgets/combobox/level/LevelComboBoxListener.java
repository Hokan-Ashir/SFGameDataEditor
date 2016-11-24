package sfgamedataeditor.common.widgets.combobox.level;

import sfgamedataeditor.fieldwrapping.AbstractFieldListener;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;

public class LevelComboBoxListener extends AbstractFieldListener<LevelComboBoxWidget, LevelComboBoxParameter> implements ItemListener {

    public LevelComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
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

//        Model<SpellParameterModelParameter> model = getModel();
//        model.getParameter().setSpellLevel(Integer.valueOf(selectedItem));
//        ShowContentViewEvent event = new ShowContentViewEvent(SpellParameterView.class, model);
//        EventProcessor.INSTANCE.process(event);
    }

    @Override
    public void updateWidgetValue(final LevelComboBoxParameter mappedObject) {
        final JComboBox<String> comboBox = getWidget().getComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
            @Override
            protected void setValues() {
                comboBox.removeAllItems();
                for (Integer spellLevel : mappedObject.getSpellLevels()) {
                    comboBox.addItem(String.valueOf(spellLevel));
                }

                comboBox.setSelectedItem(String.valueOf(mappedObject.getSelectedLevel()));
            }
        });
    }

    @Override
    protected int[] getFieldValues() {
        // TODO awkward, should be changed
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
