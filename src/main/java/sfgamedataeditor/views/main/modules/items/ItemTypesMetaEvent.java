package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class ItemTypesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowItemTypesViewEvent event = EventCreator.createEvent(ItemTypesView.class, ModulesView.class, ShowItemTypesViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(ItemTypesView.class, ModulesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowItemTypesViewEvent extends ShowViewEvent<ItemTypesView, ModulesView, Object> {

    public ShowItemTypesViewEvent(ClassTuple<ItemTypesView, ModulesView> tuple) {
        super(tuple);
    }
}
