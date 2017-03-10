package sfgamedataeditor.views.main.modules.objects.chests.parameters;

import sfgamedataeditor.database.objects.chests.ChestCorpseLootObject;
import sfgamedataeditor.mvc.objects.IconableParameter;

import javax.swing.*;
import java.util.List;

public class ChestParametersModelParameter extends IconableParameter {
    private final List<ChestCorpseLootObject> chestCorpseLootObjects;

    public ChestParametersModelParameter(List<ChestCorpseLootObject> chestCorpseLootObjects, Icon icon) {
        super(icon);
        this.chestCorpseLootObjects = chestCorpseLootObjects;
    }

    public List<ChestCorpseLootObject> getChestCorpseLootObjects() {
        return chestCorpseLootObjects;
    }
}
