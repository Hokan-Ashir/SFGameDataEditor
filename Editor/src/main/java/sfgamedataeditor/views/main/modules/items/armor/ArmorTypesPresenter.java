package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModelParameter;
import sfgamedataeditor.views.utility.ViewTools;
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
        List<SubViewPanelTuple> itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(itemPieceId);
        ArmorPiecesModelParameter parameter = new ArmorPiecesModelParameter(itemNames, null);
        return new ArmorPiecesModel(parameter);
    }
}
