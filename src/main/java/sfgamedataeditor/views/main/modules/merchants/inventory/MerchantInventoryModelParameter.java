package sfgamedataeditor.views.main.modules.merchants.inventory;

import javax.swing.*;
import java.util.List;

public class MerchantInventoryModelParameter {
    private final List<Integer> itemIds;
    private final String selectedItem;
    private final Icon icon;

    public MerchantInventoryModelParameter(List<Integer> itemIds, String selectedItem, Icon icon) {
        this.itemIds = itemIds;
        this.selectedItem = selectedItem;
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }

    public List<Integer> getItemIds() {
        return itemIds;
    }

    public String getSelectedItem() {
        return selectedItem;
    }
}
