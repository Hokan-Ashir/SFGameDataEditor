package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.ModulesView;

public class ShowItemTypesViewEvent extends ShowViewEvent<ItemTypesView, ModulesView, Object> {

    public ShowItemTypesViewEvent(ClassTuple<ItemTypesView, ModulesView> tuple) {
        super(tuple);
    }
}
