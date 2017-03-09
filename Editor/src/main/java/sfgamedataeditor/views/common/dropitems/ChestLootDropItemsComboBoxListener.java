package sfgamedataeditor.views.common.dropitems;

import sfgamedataeditor.database.objects.chests.ChestCorpseLootObject;
import sfgamedataeditor.views.main.modules.objects.chests.parameters.ChestParametersView;

public class ChestLootDropItemsComboBoxListener extends BaseDropItemsComboBoxListener<ChestCorpseLootObject, ChestParametersView> {
    public ChestLootDropItemsComboBoxListener(ChestParametersView view, Class<ChestCorpseLootObject> objectClass) {
        super(view, objectClass);
    }
}
