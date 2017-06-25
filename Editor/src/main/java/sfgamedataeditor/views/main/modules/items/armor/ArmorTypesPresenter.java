package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class ArmorTypesPresenter extends AbstractModulesPresenter<ModuleParameter, ArmorTypeListView, ArmorPiecesModel> {

    public ArmorTypesPresenter(ArmorTypeListView view) {
        super(view);
    }

    @Override
    protected ArmorPiecesModel createModel() {
        Integer itemPieceId = getView().getSelectedModuleObjectId();
        String itemPieceType = I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, String.valueOf(itemPieceId));
        List<SubViewPanelTuple> itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(Integer.parseInt(itemPieceType));
        ArmorPiecesModelParameter parameter = new ArmorPiecesModelParameter(itemNames, null);
        return new ArmorPiecesModel(parameter);
    }
}
