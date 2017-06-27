package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ArmorTypesFromArmorPiecesModelCreator implements ModelCreator<ModulesModel, ArmorPiecesModel> {

    @Override
    public ModulesModel createModel(ArmorPiecesModel childModel) {
        // TODO maybe it is important to get not first one object
        int itemId = childModel.getParameter().getSubViewPanelTuples().get(0).getObjectId();
        int typeId = ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId);
        String itemPieceNameKey = ViewTools.getKeyStringByPropertyValue(String.valueOf(typeId), I18NTypes.ITEM_TYPES_NAME_MAPPING);
        String armorPieceTypeName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, itemPieceNameKey);
        ModuleParameter parameter = new ModuleParameter(new ObjectTuple(armorPieceTypeName, typeId));
        return new ModulesModel(parameter);
    }
}
