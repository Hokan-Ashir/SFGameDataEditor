package sfgamedataeditor.views.main.modules.objects.chests;

import sfgamedataeditor.database.objects.chests.ChestCorpseLootObject;
import sfgamedataeditor.database.objects.chests.ChestCorpseLootTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.objects.chests.parameters.ChestParametersModel;
import sfgamedataeditor.views.main.modules.objects.chests.parameters.ChestParametersModelParameter;

import javax.swing.*;
import java.util.List;

public class ChestsListPresenter extends AbstractModulesPresenter<ModuleParameter, ChestsListView, ChestParametersModel> {

    public ChestsListPresenter(ChestsListView view) {
        super(view);
    }

    @Override
    protected ChestParametersModel createModel() {
        String selectedModuleName = getView().getSelectedModuleName();
        List<ChestCorpseLootObject> objectList = ChestCorpseLootTableService.INSTANCE.getChestLootObjectsByName(selectedModuleName);
        Icon icon = getView().getSelectedModuleIcon();
        ChestParametersModelParameter parameter = new ChestParametersModelParameter(objectList, icon);
        return new ChestParametersModel(parameter);
    }
}
