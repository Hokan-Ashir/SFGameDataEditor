package sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModelParameter;

import java.util.List;

public class ArmorPiecesFromArmorParametersModelCreator implements ModelCreator<ArmorPiecesModel, ArmorParametersModel> {
    @Override
    public ArmorPiecesModel createModel(ArmorParametersModel childModel) {
        ItemPriceParametersObject object = childModel.getParameter().getPriceParametersObject();
        List<ObjectTuple> itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(object.typeId);
        ObjectTuple selectedItem = TextTableService.INSTANCE.getObjectTuple(object.nameId, object.itemId);
        ArmorPiecesModelParameter parameter = new ArmorPiecesModelParameter(itemNames, selectedItem);
        return new ArmorPiecesModel(parameter);
    }
}
