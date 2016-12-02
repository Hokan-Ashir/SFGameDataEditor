package sfgamedataeditor.database.items.price.parameters;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum ItemPriceParametersTableService {
    INSTANCE;

    public void createItemPriceParametersTable() {
        CommonTableService.INSTANCE.recreateTable(ItemPriceParametersObject.class);
    }

    public void addRecordsToItemPriceParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(ItemPriceParametersObject.class, offsettedData);
    }
}
