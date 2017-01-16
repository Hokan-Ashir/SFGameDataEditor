package sfgamedataeditor.views.common.races;

import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public class AbstractRacesPresenter extends AbstractModulesPresenter<ModuleParameter, AbstractRacesView, ModulesModel> {

    public AbstractRacesPresenter(AbstractRacesView view) {
        super(view);
    }

    @Override
    protected ModulesModel createModel() {
        System.out.println();
        return null;
    }

    @Override
    public void updateView() {
        System.out.println();
    }
}
