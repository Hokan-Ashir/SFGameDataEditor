package sfgamedataeditor.views.main.modules.merchants.inventory;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.widgets.creatures.equipment.EquipmentWidget;
import sfgamedataeditor.common.widgets.creatures.equipment.EquipmentWidgetListener;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class MerchantInventoryView implements PresentableView {

    private static final Logger LOGGER = Logger.getLogger(MerchantInventoryView.class);
    private static final String ITEM_ID_FIELD_NAME = "itemId";

    private JPanel mainPanel;
    private JList<String> merchantInventoryItemList;
    private JLabel inventoryLabel;
    private JScrollPane scrollPane;
    private JPanel listPanel;
    private JPanel selectedItemPanel;
    private JLabel iconLabel;
    private final EquipmentWidget equipmentWidget;

    public MerchantInventoryView() {
        merchantInventoryItemList.setModel(new DefaultListModel<String>());
        // http://stackoverflow.com/questions/818163/make-jscrollpane-display-scrollbars-when-jlist-inside-is-changed
        merchantInventoryItemList.setPreferredSize(null);
        inventoryLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items"));

        equipmentWidget = new EquipmentWidget();
        Field itemId;
        try {
             itemId = ItemPriceParametersObject.class.getField(ITEM_ID_FIELD_NAME);
        } catch (NoSuchFieldException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        EquipmentWidgetListener listener = new EquipmentWidgetListener(equipmentWidget, itemId);
        equipmentWidget.attachListener(listener);
        selectedItemPanel.add(equipmentWidget);
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }

    JList<String> getMerchantInventoryItemList() {
        return merchantInventoryItemList;
    }

    EquipmentWidget getEquipmentWidget() {
        return equipmentWidget;
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return MerchantInventoryPresenter.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
