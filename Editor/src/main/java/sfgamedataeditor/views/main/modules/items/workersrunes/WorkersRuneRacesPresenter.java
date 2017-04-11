package sfgamedataeditor.views.main.modules.items.workersrunes;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersModel;

import javax.swing.*;
import java.util.Set;

public class WorkersRuneRacesPresenter extends AbstractModulesPresenter<ModuleParameter, WorkersRuneRacesView, WorkersRunesParametersModel> {

    private final WorkerRunesModelCreator modelCreator = new WorkerRunesModelCreator();

    public WorkersRuneRacesPresenter(WorkersRuneRacesView view) {
        super(view);
    }

    @Override
    protected WorkersRunesParametersModel createModel() {
        String selectedModuleName = getView().getSelectedModuleName();
        Integer itemType = Integer.valueOf(WorkerRunesMapping.INSTANCE.getWorkerRunesMapping(selectedModuleName));
        Set<String> runesNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(itemType);
        String runeName = runesNames.iterator().next();
        Integer itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(runeName, itemType);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }
}
