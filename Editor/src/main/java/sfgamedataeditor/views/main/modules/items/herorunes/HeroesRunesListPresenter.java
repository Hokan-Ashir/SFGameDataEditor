package sfgamedataeditor.views.main.modules.items.herorunes;

import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.herorunes.parameters.HeroesRunesParametersModel;

public class HeroesRunesListPresenter extends AbstractModulesPresenter<ModuleParameter, HeroesRunesListView, HeroesRunesParametersModel> {

    private final HeroRunesModelCreator modelCreator = new HeroRunesModelCreator();

    public HeroesRunesListPresenter(HeroesRunesListView view) {
        super(view);
    }

    @Override
    protected HeroesRunesParametersModel createModel() {
        Integer itemId = getView().getSelectedModuleObjectId();
        return modelCreator.createModel(itemId);
    }
}
