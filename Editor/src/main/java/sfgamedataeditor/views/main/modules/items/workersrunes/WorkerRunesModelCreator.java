package sfgamedataeditor.views.main.modules.items.workersrunes;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersModel;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersModelParameter;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerRunesModelCreator implements ModelCreator<WorkersRunesParametersModel> {
    @Override
    public WorkersRunesParametersModel createModel(int objectId) {
        // TODO improve performance, maybe =)
        ItemPriceParametersObject object = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(objectId);
        List<ObjectTuple> tuples = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(object.typeId);
        Map<Integer, Integer> runeLevelToItemIdMap = new HashMap<>();
        for (ObjectTuple tuple : tuples) {
            ItemPriceParametersObject runeObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(tuple.getObjectId());
            CreatureParameterObject creatureObjectByStatsId = CreatureParametersTableService.INSTANCE.getCreatureObjectByStatsId(runeObject.unitStatsId);
            runeLevelToItemIdMap.put(creatureObjectByStatsId.level, runeObject.itemId);
        }

        ObjectTuple itemName = TextTableService.INSTANCE.getObjectTuple(object.nameId, objectId);
        Icon icon = ImageIconsCache.INSTANCE.getImageIcon(getIconPath(), objectId);
        WorkersRunesParametersModelParameter parameter = new WorkersRunesParametersModelParameter(itemName.getName(), 1, runeLevelToItemIdMap, icon);
        return new WorkersRunesParametersModel(parameter);
    }

    @Override
    public String getIconPath() {
        return "/images/races/";
    }
}
