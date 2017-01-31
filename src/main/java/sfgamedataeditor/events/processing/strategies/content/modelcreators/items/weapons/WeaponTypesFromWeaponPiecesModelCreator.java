package sfgamedataeditor.events.processing.strategies.content.modelcreators.items.weapons;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModel;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class WeaponTypesFromWeaponPiecesModelCreator implements ModelCreator<ModulesModel, WeaponPiecesModel> {

    @Override
    public ModulesModel createModel(WeaponPiecesModel childModel) {
        // TODO maybe it is important to get not first one object
        // TODO remove duplication and generalize item types views
        String selectedWeaponPieceName = childModel.getParameter().getSubPanelsNames().iterator().next();
        int itemId = ViewTools.getKeyByPropertyValue(selectedWeaponPieceName, I18NTypes.ITEMS);

        String typeId = String.valueOf(ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId));
        String itemPieceNameKey = ViewTools.getKeyStringByPropertyValue(typeId, I18NTypes.ITEM_PIECES_NAME_MAPPING);
        String armorPieceTypeName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, itemPieceNameKey);
        ModuleParameter parameter = new ModuleParameter(armorPieceTypeName);
        return new ModulesModel(parameter);
    }
}
