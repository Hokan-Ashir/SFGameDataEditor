package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModelParameter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;

public class ArmorTypesPresenter extends AbstractModulesPresenter<ModuleParameter, ArmorTypeListView, ArmorPiecesModel> {

    public ArmorTypesPresenter(ArmorTypeListView view) {
        super(view);
    }

    @Override
    protected ArmorPiecesModel createModel() {
        String selectedArmorPieceType = getView().getSelectedModuleValue();
        String itemPieceId = ViewTools.getKeyStringByPropertyValue(selectedArmorPieceType, I18NTypes.COMMON);
        String itemPieceType = I18NService.INSTANCE.getMessage(I18NTypes.ITEM_PIECES_NAME_MAPPING, itemPieceId);
        Set<String> itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(Integer.parseInt(itemPieceType));
        ArmorPiecesModelParameter parameter = new ArmorPiecesModelParameter(itemNames, null);
        return new ArmorPiecesModel(parameter);
    }
}
