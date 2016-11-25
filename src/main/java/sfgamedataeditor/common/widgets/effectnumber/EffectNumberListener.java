package sfgamedataeditor.common.widgets.effectnumber;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.objects.OffsetableObject;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;

public class EffectNumberListener extends AbstractWidgetListener<EffectNumberWidget, OffsetableObject> implements ItemListener {

    public EffectNumberListener(EffectNumberWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        // TODO seems awkward, need to be fixed
        return null;
    }

    @Override
    protected void setFieldValues(int[] value) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
