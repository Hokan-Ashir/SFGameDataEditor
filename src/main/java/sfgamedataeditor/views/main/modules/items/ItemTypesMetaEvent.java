package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class ItemTypesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowItemTypesViewEvent event = EventCreator.createEvent(ItemTypesView.class, ModulesView.class, ShowItemTypesViewEvent.class);
        addEvent(event);
    }
}

class ShowItemTypesViewEvent extends ShowViewEvent<ItemTypesView, ModulesView, Object> {

    public ShowItemTypesViewEvent(ClassTuple<ItemTypesView, ModulesView> tuple) {
        super(tuple);
    }
}
