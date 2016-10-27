package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class MerchantLocationsMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(ModulesView.class, MainView.class);
        ShowMerchantsLocationsViewEvent event = EventCreator.createEvent(MerchantLocationsView.class, MainView.class, ShowMerchantsLocationsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent<>(MerchantLocationsView.class, MainView.class);
        addEvents(moduleNameEvent, event, clearViewEvent);
    }
}

class ShowMerchantsLocationsViewEvent extends ShowViewEvent<MerchantLocationsView, MainView, Object> {

    public ShowMerchantsLocationsViewEvent(ClassTuple<MerchantLocationsView, MainView> tuple) {
        super(tuple);
    }
}
