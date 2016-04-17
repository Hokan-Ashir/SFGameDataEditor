package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.ModulesView;

public class ShowMerchantsLocationsViewEvent extends ShowViewEvent<MerchantLocationsView, ModulesView, Object> {

    public ShowMerchantsLocationsViewEvent(ClassTuple<MerchantLocationsView, ModulesView> tuple) {
        super(tuple);
    }
}
