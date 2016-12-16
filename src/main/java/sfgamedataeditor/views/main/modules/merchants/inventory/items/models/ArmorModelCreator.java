package sfgamedataeditor.views.main.modules.merchants.inventory.items.models;

import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsTableService;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersModelParameter;

public class ArmorModelCreator implements ModelCreator<ArmorParametersModel> {

    @Override
    public ArmorParametersModel createModel(int itemId) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        ArmorParametersObject armorParametersObject = ArmorParametersTableService.INSTANCE.getObjectByItemId(itemId);
        ItemRequirementsObject requirementsObject = ItemRequirementsTableService.INSTANCE.getObjectByItemId(itemId);
        ArmorParametersModelParameter parameter = new ArmorParametersModelParameter(itemPriceObject, armorParametersObject, requirementsObject);
        return new ArmorParametersModel(parameter);
    }
}
