package sfgamedataeditor.events.processing.strategies.content.modelcreators.items.armor;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModel;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ArmorTypesFromArmorPiecesModelCreator implements ModelCreator<ModulesModel, ArmorPiecesModel> {

    @Override
    public ModulesModel createModel(ArmorPiecesModel childModel) {
        // TODO maybe it is important to get not first one object
        String selectedArmorPieceName = childModel.getParameter().getArmorPiecesNames().get(0);
        int itemId = ViewTools.getKeyByPropertyValue(selectedArmorPieceName, I18NTypes.ITEMS);

        String typeId = String.valueOf(ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId));
        String itemPieceNameKey = ViewTools.getKeyStringByPropertyValue(typeId, I18NTypes.ITEM_PIECES_NAME_MAPPING);
        String armorPieceTypeName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, itemPieceNameKey);
        ModuleParameter parameter = new ModuleParameter(armorPieceTypeName);
        return new ModulesModel(parameter);
    }
}
