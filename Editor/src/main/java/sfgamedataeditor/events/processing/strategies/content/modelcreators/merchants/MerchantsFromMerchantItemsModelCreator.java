package sfgamedataeditor.events.processing.strategies.content.modelcreators.merchants;

import sfgamedataeditor.database.merchants.inventory.MerchantInventoryTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.merchants.inventory.MerchantInventoryModel;

import java.util.List;

public class MerchantsFromMerchantItemsModelCreator implements ModelCreator<ModulesModel, MerchantInventoryModel> {

    @Override
    public ModulesModel createModel(MerchantInventoryModel childModel) {
        List<Integer> itemIds = childModel.getParameter().getItemIds();
        String merchantName = MerchantInventoryTableService.INSTANCE.getMerchantNameByItemId(itemIds);
        ModuleParameter parameter = new ModuleParameter(merchantName);
        return new ModulesModel(parameter);
    }
}
