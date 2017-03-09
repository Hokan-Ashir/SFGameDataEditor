package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.LevelableParameter;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.PresentableView;
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

    private void processSelectedItemEvent(String selectedItem) {
        Model oldModel = ViewRegister.INSTANCE.getViews().get(getViewClass()).getPresenter().getModel();
        ((LevelableParameter) oldModel.getParameter()).setLevel(Integer.valueOf(selectedItem));
        ShowContentViewEvent event = new ShowContentViewEvent(getViewClass(), oldModel);
        EventProcessor.INSTANCE.process(event);
    }

    protected abstract Class<? extends PresentableView> getViewClass();

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
