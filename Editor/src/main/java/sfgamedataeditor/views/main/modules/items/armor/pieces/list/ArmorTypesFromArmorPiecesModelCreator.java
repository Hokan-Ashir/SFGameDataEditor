package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ArmorTypesFromArmorPiecesModelCreator implements ModelCreator<ModulesModel, ArmorPiecesModel> {

    private static final Integer[] ARMOR_TYPES_IDS = new Integer[] {
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.helmets")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.chest.armor")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.robes")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.legs.armor")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.shield")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.rings"))
    };

    @Override
    public ModulesModel createModel(ArmorPiecesModel childModel) {
        // TODO maybe it is important to get not first one object
        int itemId = childModel.getParameter().getSubViewPanelTuples().get(0).getObjectId();
        String typeId = String.valueOf(ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId));
        String itemPieceNameKey = ViewTools.getKeyStringByPropertyValue(typeId, I18NTypes.ITEM_TYPES_NAME_MAPPING);
        String armorPieceTypeName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, itemPieceNameKey);
        ModuleParameter parameter = new ModuleParameter(armorPieceTypeName);
        return new ModulesModel(parameter);
    }
}
