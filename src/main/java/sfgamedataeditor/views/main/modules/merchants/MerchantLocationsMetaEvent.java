package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class MerchantLocationsMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowMerchantsLocationsViewEvent event = EventCreator.createEvent(MerchantLocationsView.class, ModulesView.class, ShowMerchantsLocationsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(MerchantLocationsView.class, ModulesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowMerchantsLocationsViewEvent extends ShowViewEvent<MerchantLocationsView, ModulesView, Object> {

    public ShowMerchantsLocationsViewEvent(ClassTuple<MerchantLocationsView, ModulesView> tuple) {
        super(tuple);
    }
}
