package sfgamedataeditor.views.main.modules.objects.chests.parameters;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.objects.chests.ChestCorpseLootObject;
import sfgamedataeditor.database.objects.chests.ChestCorpseLootTableService;
import sfgamedataeditor.views.common.model.creators.ModelCreator;

import javax.swing.*;
import java.util.List;

public class ChestLootModelCreator implements ModelCreator<ChestParametersModel> {
    @Override
    public ChestParametersModel createModel(int objectId) {
        List<ChestCorpseLootObject> objectList = ChestCorpseLootTableService.INSTANCE.getChestLootObjectsById(objectId);
        Icon icon = ImageIconsCache.INSTANCE.getImageIcon(getIconPath(), objectId);
        ChestParametersModelParameter parameter = new ChestParametersModelParameter(objectList, icon);
        return new ChestParametersModel(parameter);
    }

    @Override
    public String getIconPath() {
        return "";
    }
}
