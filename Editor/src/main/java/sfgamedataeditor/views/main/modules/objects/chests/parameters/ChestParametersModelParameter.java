package sfgamedataeditor.views.main.modules.objects.chests.parameters;

import sfgamedataeditor.database.objects.chests.ChestCorpseLootObject;

import javax.swing.*;
import java.util.List;

public class ChestParametersModelParameter {
    private final List<ChestCorpseLootObject> chestCorpseLootObjects;
    private final Icon icon;

    public ChestParametersModelParameter(List<ChestCorpseLootObject> chestCorpseLootObjects, Icon icon) {
        this.chestCorpseLootObjects = chestCorpseLootObjects;
        this.icon = icon;
    }

    public List<ChestCorpseLootObject> getChestCorpseLootObjects() {
        return chestCorpseLootObjects;
    }

    public Icon getIcon() {
        return icon;
    }
}
