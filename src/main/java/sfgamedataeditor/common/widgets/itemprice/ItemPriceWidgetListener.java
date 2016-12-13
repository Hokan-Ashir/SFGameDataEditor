package sfgamedataeditor.common.widgets.itemprice;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.lang.reflect.Field;

public class ItemPriceWidgetListener extends AbstractWidgetListener<ItemPriceWidget, OffsetableObject> implements DocumentListener {

    protected ItemPriceWidgetListener(ItemPriceWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        return new int[0];
    }

    @Override
    protected void setFieldValues(int[] value) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
