package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersModel;

public class MiscellaneousPresenter extends AbstractModulesPresenter<ModuleParameter, MiscellaneousListView, MiscellaneousParametersModel> {

    private final MiscellaneousModelCreator modelCreator = new MiscellaneousModelCreator();

    public MiscellaneousPresenter(MiscellaneousListView view) {
        super(view);
    }

    @Override
    protected MiscellaneousParametersModel createModel() {
        int itemId = getView().getSelectedModuleObjectId();
        return modelCreator.createModel(itemId);
    }
}
