package sfgamedataeditor.events.processing.strategies.content.modelcreators.items.armor;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModelParameter;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;

public class ArmorPiecesFromArmorParametersModelCreator implements ModelCreator<ArmorPiecesModel, ArmorParametersModel> {
    @Override
    public ArmorPiecesModel createModel(ArmorParametersModel childModel) {
        Integer itemId = childModel.getParameter().getPriceParametersObject().itemId;
        Integer typeId = childModel.getParameter().getPriceParametersObject().typeId;
        Set<String> itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(typeId);
        String selectedItem = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(itemId));
        ArmorPiecesModelParameter parameter = new ArmorPiecesModelParameter(itemNames, selectedItem);
        return new ArmorPiecesModel(parameter);
    }
}
