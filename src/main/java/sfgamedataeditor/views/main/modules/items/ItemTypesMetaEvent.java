package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class ItemTypesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(ModulesView.class, MainView.class);
        ShowItemTypesViewEvent event = EventCreator.createEvent(ItemTypesView.class, MainView.class, ShowItemTypesViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent<>(ItemTypesView.class, MainView.class);
        addEvents(moduleNameEvent, event, clearViewEvent);
    }
}

class ShowItemTypesViewEvent extends ShowViewEvent<ItemTypesView, MainView, Object> {

    public ShowItemTypesViewEvent(ClassTuple<ItemTypesView, MainView> tuple) {
        super(tuple);
    }
}
