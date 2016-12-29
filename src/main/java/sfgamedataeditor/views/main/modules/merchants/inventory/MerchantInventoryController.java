package sfgamedataeditor.views.main.modules.merchants.inventory;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class MerchantInventoryController extends AbstractController<MerchantInventoryModelParameter, MerchantInventoryView> implements ListSelectionListener {

    public MerchantInventoryController(MerchantInventoryView view) {
        super(view);
        getView().getMerchantInventoryItemList().addListSelectionListener(this);
    }

    @Override
    public void updateView() {
        getView().getEquipmentWidget().setVisible(false);
        List<Integer> itemIds = getModel().getParameter().getItemIds();
        DefaultListModel model = (DefaultListModel) getView().getMerchantInventoryItemList().getModel();
        model.removeAllElements();

        getView().getMerchantInventoryItemList().removeListSelectionListener(this);
        for (Integer itemId : itemIds) {
            String itemName = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(itemId));
            model.addElement(itemName);
        }
        getView().getMerchantInventoryItemList().addListSelectionListener(this);

        getView().getMerchantInventoryItemList().setSelectedValue(getModel().getParameter().getSelectedItem(), true);
    }

    @Override
    public void renderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideContentPanel(getView());
    }

    @Override
    public void unRenderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.unRenderViewInsideContentPanel(getView());
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
