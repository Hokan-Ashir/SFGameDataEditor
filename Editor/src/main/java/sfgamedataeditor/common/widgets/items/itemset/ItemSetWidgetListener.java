package sfgamedataeditor.common.widgets.items.itemset;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;

public class ItemSetWidgetListener extends AbstractWidgetListener<ItemSetWidget, OffsetableObject> implements ItemListener {

    public ItemSetWidgetListener(ItemSetWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        String selectedItem = (String) getWidget().getItemSetNameComboBox().getSelectedItem();
        Integer itemSetNumber = ViewTools.getKeyByPropertyValue(selectedItem, I18NTypes.ITEM_SETS);
        return new int[] {itemSetNumber};
    }

    @Override
    protected void setFieldValues(int[] value) {
        String itemSetName = I18NService.INSTANCE.getMessage(I18NTypes.ITEM_SETS, String.valueOf(value[0]));
        getWidget().getItemSetNameComboBox().setSelectedItem(itemSetName);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        String selectedItem = (String) getWidget().getItemSetNameComboBox().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        getWidget().updateItemSetDescription(selectedItem);
        setWidgetValueToDTOField();
    }
}
