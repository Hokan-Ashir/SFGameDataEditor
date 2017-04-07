package sfgamedataeditor.views.main.modules.objects.chests.parameters;

import sfgamedataeditor.database.objects.chests.ChestCorpseLootObject;
import sfgamedataeditor.database.objects.chests.ChestCorpseLootTableService;
import sfgamedataeditor.views.common.model.creators.ModelCreator;

import javax.swing.*;
import java.util.List;

public class ChestLootModelCreator implements ModelCreator<ChestParametersModel> {
    @Override
    public ChestParametersModel createModel(int objectId, Icon icon) {
        List<ChestCorpseLootObject> objectList = ChestCorpseLootTableService.INSTANCE.getChestLootObjectsById(objectId);
        ChestParametersModelParameter parameter = new ChestParametersModelParameter(objectList, icon);
        return new ChestParametersModel(parameter);
    }
}
