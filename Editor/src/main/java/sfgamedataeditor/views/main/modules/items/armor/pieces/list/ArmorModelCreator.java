package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsTableService;
import sfgamedataeditor.views.main.modules.ModelCreator;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersModelParameter;

import javax.swing.*;
import java.util.List;

public class ArmorModelCreator implements ModelCreator<ArmorParametersModel> {

    @Override
    public ArmorParametersModel createModel(int itemId, Icon icon) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        ArmorParametersObject armorParametersObject = ArmorParametersTableService.INSTANCE.getObjectByItemId(itemId);
        List<ItemRequirementsObject> requirementsObjects = ItemRequirementsTableService.INSTANCE.getObjectsByItemId(itemId);
        ArmorParametersModelParameter parameter = new ArmorParametersModelParameter(itemPriceObject, armorParametersObject, requirementsObjects);
        return new ArmorParametersModel(parameter);
    }
}
