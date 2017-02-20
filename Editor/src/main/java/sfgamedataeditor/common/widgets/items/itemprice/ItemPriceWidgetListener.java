package sfgamedataeditor.common.widgets.items.itemprice;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;
import sfgamedataeditor.views.utility.notification.Notification;
import sfgamedataeditor.views.utility.notification.NotificationType;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.lang.reflect.Field;

public class ItemPriceWidgetListener extends AbstractWidgetListener<ItemPriceWidget, OffsetableObject> implements DocumentListener {

    private static final Integer CURRENCY_MODIFICATOR = 100;

    public ItemPriceWidgetListener(ItemPriceWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        Integer goldAmount = getWidget().getGoldAmount();
        Integer silverAmount = getWidget().getSilverAmount();
        Integer copperAmount = getWidget().getCopperAmount();
        Integer totalAmount = goldAmount * CURRENCY_MODIFICATOR * CURRENCY_MODIFICATOR + silverAmount * CURRENCY_MODIFICATOR + copperAmount;
        return new int[]{totalAmount};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int copperBasedCost = value[0];
        int goldAmount = copperBasedCost / (CURRENCY_MODIFICATOR * CURRENCY_MODIFICATOR);
        int silverAmount = (copperBasedCost - goldAmount * CURRENCY_MODIFICATOR * CURRENCY_MODIFICATOR) / CURRENCY_MODIFICATOR;
        int copperAmount = ((copperBasedCost - goldAmount * CURRENCY_MODIFICATOR * CURRENCY_MODIFICATOR) - silverAmount * CURRENCY_MODIFICATOR) / CURRENCY_MODIFICATOR;

        getWidget().setGoldAmount(goldAmount);
        getWidget().setSilverAmount(silverAmount);
        getWidget().setCopperAmount(copperAmount);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changeValue(e.getDocument());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changeValue(e.getDocument());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        changeValue(e.getDocument());
    }

    private void changeValue(Document document) {
        boolean fieldIsEmpty = getWidget().isFieldOfDocumentIsEmpty(document);
        if (fieldIsEmpty) {
            return;
        }

        // TODO add gold value limit with notification
        boolean isGoldAmountField = getWidget().isGoldAmountField(document);
        if (!isGoldAmountField) {
            Integer value = getWidget().getFieldValue(document);
            try {
                if (value < 0) {
                    new Notification(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "errorNumberLessThanZero"), NotificationType.ERROR);
                    return;
                }

                if (value >= CURRENCY_MODIFICATOR) {
                    new Notification(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "error.exceeds.max.value")
                            + ": [" + String.valueOf(CURRENCY_MODIFICATOR) + "]", NotificationType.ERROR);
                    return;
                }

            } catch (NumberFormatException e) {
                new Notification(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "errorNotANumber"), NotificationType.ERROR);
                return;
            }
        }

        setWidgetValueToDTOField();
    }
}
