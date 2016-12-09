package sfgamedataeditor.events.processing.strategies.content.modelcreators.items;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ResourceBundle;
import java.util.Set;

public class ArmorTypesFromArmorPiecesModelCreator implements ModelCreator<ModulesModel, ArmorPiecesModel> {

    @Override
    public ModulesModel createModel(ArmorPiecesModel childModel) {
        // TODO maybe it is important to get not first one object
        String selectedArmorPieceName = childModel.getParameter().getArmorPiecesNames().get(0);
        ResourceBundle itemsBundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEMS);
        Set<String> keys = itemsBundle.keySet();

        int itemId = 0;
        for (String key : keys) {
            if (itemsBundle.getString(key).equals(selectedArmorPieceName)) {
                itemId = Integer.parseInt(key);
                break;
            }
        }

        String typeId = String.valueOf(ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId));
        ResourceBundle itemPiecesBundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEM_PIECES_NAME_MAPPING);
        Set<String> strings = itemPiecesBundle.keySet();
        String itemPieceNameKey = null;
        for (String string : strings) {
            if (itemPiecesBundle.getString(string).equals(typeId)) {
                itemPieceNameKey = string;
                break;
            }
        }

        String armorPieceTypeName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, itemPieceNameKey);
        ModuleParameter parameter = new ModuleParameter(armorPieceTypeName);
        return new ModulesModel(parameter);
    }
}
