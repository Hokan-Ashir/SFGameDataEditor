package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModel;

import javax.swing.*;

public class CreaturesPresenter extends AbstractModulesPresenter<CreaturesModelParameter, CreaturesView, CreaturesParametersModel> {

    private CreaturesModelCreator modelCreator = new CreaturesModelCreator();

    public CreaturesPresenter(CreaturesView view) {
        super(view);
    }

    @Override
    protected CreaturesParametersModel createModel() {
        String selectedCreatureName = getView().getSelectedModuleName();
        Integer creatureId = CreatureCommonParametersTableService.INSTANCE.getCreatureIdByName(selectedCreatureName);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(creatureId, icon);
    }
}
