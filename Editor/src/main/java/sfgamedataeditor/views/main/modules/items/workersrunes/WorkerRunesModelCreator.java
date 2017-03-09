package sfgamedataeditor.views.main.modules.items.workersrunes;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModelCreator;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersModel;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersModelParameter;

import javax.swing.*;
import java.util.Set;

public class WorkerRunesModelCreator implements ModelCreator<WorkersRunesParametersModel> {
    @Override
    public WorkersRunesParametersModel createModel(int objectId, Icon icon) {
        // TODO each model creator expects to get OBJECT id and icon,
        // though worker runes model creator expects to pass worker rune TYPE, which broke EquipmentWidgetListener
        Set<String> runesNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(objectId);
        String runeName = runesNames.iterator().next().split(" - ")[0];
        WorkersRunesParametersModelParameter parameter = new WorkersRunesParametersModelParameter(runeName, 1, icon);
        return new WorkersRunesParametersModel(parameter);
    }
}
