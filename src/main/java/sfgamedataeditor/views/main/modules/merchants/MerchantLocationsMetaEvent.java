package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class MerchantLocationsMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowMerchantsLocationsViewEvent event = EventCreator.createEvent(MerchantLocationsView.class, ModulesView.class, ShowMerchantsLocationsViewEvent.class);
        addEvent(event);
    }
}

class ShowMerchantsLocationsViewEvent extends ShowViewEvent<MerchantLocationsView, ModulesView, Object> {

    public ShowMerchantsLocationsViewEvent(ClassTuple<MerchantLocationsView, ModulesView> tuple) {
        super(tuple);
    }
}
