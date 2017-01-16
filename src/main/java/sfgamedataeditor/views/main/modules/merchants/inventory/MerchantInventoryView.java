package sfgamedataeditor.views.main.modules.merchants.inventory;

import sfgamedataeditor.common.widgets.creatures.equipment.EquipmentWidget;
import sfgamedataeditor.common.widgets.creatures.equipment.EquipmentWidgetListener;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.lang.reflect.Field;

public class MerchantInventoryView implements PresentableView {

    private JPanel mainPanel;
    private JList<String> merchantInventoryItemList;
    private JLabel inventoryLabel;
    private JScrollPane scrollPane;
    private JPanel listPanel;
    private JPanel selectedItemPanel;
    private final EquipmentWidget equipmentWidget;

    public MerchantInventoryView() {
        merchantInventoryItemList.setModel(new DefaultListModel<String>());
        // http://stackoverflow.com/questions/818163/make-jscrollpane-display-scrollbars-when-jlist-inside-is-changed
        merchantInventoryItemList.setPreferredSize(null);
        inventoryLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items"));

        equipmentWidget = new EquipmentWidget();
        Field itemId = null;
        try {
             itemId = ItemPriceParametersObject.class.getField("itemId");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        EquipmentWidgetListener listener = new EquipmentWidgetListener(equipmentWidget, itemId);
        equipmentWidget.attachListener(listener);
        selectedItemPanel.add(equipmentWidget);
    }

    public JList<String> getMerchantInventoryItemList() {
        return merchantInventoryItemList;
    }

    public EquipmentWidget getEquipmentWidget() {
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
