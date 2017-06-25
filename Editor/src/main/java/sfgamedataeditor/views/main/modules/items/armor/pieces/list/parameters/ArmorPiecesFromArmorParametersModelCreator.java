package sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.text.TextObject;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class ArmorPiecesFromArmorParametersModelCreator implements ModelCreator<ArmorPiecesModel, ArmorParametersModel> {
    @Override
    public ArmorPiecesModel createModel(ArmorParametersModel childModel) {
        Integer nameId = childModel.getParameter().getPriceParametersObject().nameId;
        Integer typeId = childModel.getParameter().getPriceParametersObject().typeId;
        List<SubViewPanelTuple> itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(typeId);
        String selectedItem = TextTableService.INSTANCE.getObjectName(nameId);
        ArmorPiecesModelParameter parameter = new ArmorPiecesModelParameter(itemNames, selectedItem);
        return new ArmorPiecesModel(parameter);
    }
}
