package sfgamedataeditor.common.widgets.items.itemset;

import i18nbase.objects.I18NObject;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

public class ItemSetWidget extends AbstractWidget<ItemSetWidgetListener> {

    private static final String DESCRIPTION_POSTFIX = "description";

    private JPanel mainPanel;
    private JComboBox<String> itemSetNameComboBox;
    private JLabel titleLabel;
    private JTextArea itemSetDescriptionArea;

    public ItemSetWidget() {
        List<? extends I18NObject> i18NObjects = I18NService.INSTANCE.getI18NObjects(I18NTypes.ITEM_SETS);
        for (I18NObject i18NObject : i18NObjects) {
            if (!i18NObject.key.contains(DESCRIPTION_POSTFIX)) {
                itemSetNameComboBox.addItem(i18NObject.value);
            }
        }

        add(getMainPanel());
    }

    public JComboBox<String> getItemSetNameComboBox() {
        return itemSetNameComboBox;
    }

    public void updateItemSetDescription(String itemSetName) {
        String itemSetNumber = ViewTools.getKeyStringByPropertyValue(itemSetName, I18NTypes.ITEM_SETS);
        itemSetNumber += "." + DESCRIPTION_POSTFIX;
        itemSetDescriptionArea.setText(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_SETS, itemSetNumber));
    }

    @Override
    protected void insertListener(ItemSetWidgetListener listener) {
        itemSetNameComboBox.addItemListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {
        titleLabel.setText(i18nStrings.get(0));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
