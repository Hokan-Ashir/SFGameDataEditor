package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModelParameter;

import java.util.List;

public class ArmorTypesPresenter extends AbstractModulesPresenter<ModuleParameter, ArmorTypeListView, ArmorPiecesModel> {

    public ArmorTypesPresenter(ArmorTypeListView view) {
        super(view);
    }

    @Override
    protected ArmorPiecesModel createModel() {
        Integer itemPieceId = getView().getSelectedModuleObjectId();
        List<ObjectTuple> itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(itemPieceId);
        ArmorPiecesModelParameter parameter = new ArmorPiecesModelParameter(itemNames, null);
        return new ArmorPiecesModel(parameter);
    }
}
