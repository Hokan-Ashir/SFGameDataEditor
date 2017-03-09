package sfgamedataeditor.views.common.dropitems;

import sfgamedataeditor.database.creatures.corpseloot.CreatureCorpseLootObject;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;

public class CreaturesDropItemsComboBoxListener extends BaseDropItemsComboBoxListener<CreatureCorpseLootObject, CreaturesParametersView> {
    public CreaturesDropItemsComboBoxListener(CreaturesParametersView view, Class<CreatureCorpseLootObject> objectClass) {
        super(view, objectClass);
    }
}
