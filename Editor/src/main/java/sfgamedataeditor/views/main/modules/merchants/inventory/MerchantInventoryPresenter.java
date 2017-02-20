package sfgamedataeditor.views.main.modules.merchants.inventory;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class MerchantInventoryPresenter extends AbstractPresenter<MerchantInventoryModelParameter, MerchantInventoryView> implements ListSelectionListener {

    public MerchantInventoryPresenter(MerchantInventoryView view) {
        super(view);
        getView().getMerchantInventoryItemList().addListSelectionListener(this);
    }

    @Override
    public void updateView() {
        getView().getEquipmentWidget().setVisible(false);
        MerchantInventoryModelParameter parameter = getModel().getParameter();
        List<Integer> itemIds = parameter.getItemIds();
        String selectedItem = parameter.getSelectedItem();
        updateMerchantInventoryList(itemIds, selectedItem);

        Icon icon = parameter.getIcon();
        getView().getIconLabel().setIcon(icon);
    }

    private void updateMerchantInventoryList(List<Integer> itemIds, String selectedItem) {
        JList<String> inventoryItemList = getView().getMerchantInventoryItemList();
        DefaultListModel model = (DefaultListModel) inventoryItemList.getModel();
        model.removeAllElements();

        inventoryItemList.removeListSelectionListener(this);
        for (Integer itemId : itemIds) {
            String itemName = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(itemId));
            model.addElement(itemName);
        }
        inventoryItemList.addListSelectionListener(this);
        inventoryItemList.setSelectedValue(selectedItem, true);
    }

    @Override
    public void renderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideContentPanel(getView().getMainPanel());
    }

    @Override
    public void unRenderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.unRenderViewInsideContentPanel(getView().getMainPanel());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // do not catch other events except last one
        if (e.getValueIsAdjusting()) {
            return;
        }
        ListModel<String> model = getView().getMerchantInventoryItemList().getModel();
        if (model.getSize() == 0) {
            return;
        }

        getView().getEquipmentWidget().setVisible(true);
        // because list permit only to select one item at a time, it's not important which (first or last) selected
        // index get from click event
        int firstIndex = e.getFirstIndex();
        Integer itemId = getModel().getParameter().getItemIds().get(firstIndex);
        ItemPriceParametersObject parametersObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        getView().getEquipmentWidget().getListener().updateWidgetValue(parametersObject);
    }
}
