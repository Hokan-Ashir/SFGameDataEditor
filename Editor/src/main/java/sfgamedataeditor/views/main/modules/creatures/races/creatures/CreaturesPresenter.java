package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModel;

import javax.swing.*;

public class CreaturesPresenter extends AbstractModulesPresenter<CreaturesModelParameter, CreaturesView, CreaturesParametersModel> {

    private final CreaturesModelCreator modelCreator = new CreaturesModelCreator();

    public CreaturesPresenter(CreaturesView view) {
        super(view);
    }

    @Override
    protected CreaturesParametersModel createModel() {
        Integer creatureId =  getView().getSelectedModuleObjectId();
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(creatureId);
    }
}
