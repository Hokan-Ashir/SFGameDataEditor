package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersModel;

public class ArmorPiecesPresenter extends AbstractModulesPresenter<ArmorPiecesModelParameter, ArmorPiecesView, ArmorParametersModel> {

    private final ArmorModelCreator modelCreator = new ArmorModelCreator();

    public ArmorPiecesPresenter(ArmorPiecesView view) {
        super(view);
    }

    @Override
    protected ArmorParametersModel createModel() {
        int itemId = getView().getSelectedModuleObjectId();
        return modelCreator.createModel(itemId);
    }
}
