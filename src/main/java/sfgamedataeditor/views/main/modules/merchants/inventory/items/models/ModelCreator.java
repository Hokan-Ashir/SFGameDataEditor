package sfgamedataeditor.views.main.modules.merchants.inventory.items.models;

import sfgamedataeditor.mvc.objects.Model;

public interface ModelCreator<C extends Model> {
    C createModel(int itemId);
}
