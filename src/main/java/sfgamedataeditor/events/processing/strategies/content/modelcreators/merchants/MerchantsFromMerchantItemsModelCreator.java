package sfgamedataeditor.events.processing.strategies.content.modelcreators.merchants;

import sfgamedataeditor.database.merchants.inventory.MerchantInventoryTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.merchants.inventory.MerchantInventoryModel;

public class MerchantsFromMerchantItemsModelCreator implements ModelCreator<ModulesModel, MerchantInventoryModel> {

    @Override
    public ModulesModel createModel(MerchantInventoryModel childModel) {
        // TODO maybe it's important to select which item to select in list
        Integer itemId = childModel.getParameter().getItemIds().get(0);
        String merchantName = MerchantInventoryTableService.INSTANCE.getMerchantNameByItemId(itemId);
        ModuleParameter parameter = new ModuleParameter(merchantName);
        return new ModulesModel(parameter);
    }
}
