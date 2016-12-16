package sfgamedataeditor.views.main.modules.merchants.inventory;

import java.util.List;

public class MerchantInventoryModelParameter {
    private final List<Integer> itemIds;
    private final String selectedItem;

    public MerchantInventoryModelParameter(List<Integer> itemIds, String selectedItem) {
        this.itemIds = itemIds;
        this.selectedItem = selectedItem;
    }

    public List<Integer> getItemIds() {
        return itemIds;
    }

    public String getSelectedItem() {
        return selectedItem;
    }
}
