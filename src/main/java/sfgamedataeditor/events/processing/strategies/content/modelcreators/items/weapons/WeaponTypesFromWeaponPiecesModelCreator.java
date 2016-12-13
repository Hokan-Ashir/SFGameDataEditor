package sfgamedataeditor.events.processing.strategies.content.modelcreators.items.weapons;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ResourceBundle;
import java.util.Set;

public class WeaponTypesFromWeaponPiecesModelCreator implements ModelCreator<ModulesModel, WeaponPiecesModel> {

    @Override
    public ModulesModel createModel(WeaponPiecesModel childModel) {
        // TODO maybe it is important to get not first one object
        // TODO remove duplication and generalize item types views
        String selectedWeaponPieceName = childModel.getParameter().getWeaponPiecesNames().get(0);
        ResourceBundle itemsBundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEMS);
        Set<String> keys = itemsBundle.keySet();

        int itemId = 0;
        for (String key : keys) {
            if (itemsBundle.getString(key).equals(selectedWeaponPieceName)) {
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
