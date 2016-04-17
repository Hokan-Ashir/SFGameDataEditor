package sfgamedataeditor.views.main.modules;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.views.main.modules.buildings.ShowBuildingRacesViewEvent;
import sfgamedataeditor.views.main.modules.items.ShowItemTypesViewEvent;
import sfgamedataeditor.views.main.modules.merchants.ShowMerchantsLocationsViewEvent;
import sfgamedataeditor.views.main.modules.skills.schools.ShowSkillSchoolsViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.ShowSpellSchoolsViewEvent;
import sfgamedataeditor.views.main.modules.units.ShowUnitsRacesViewEvent;

public class ModulesEventHandler {

    @EventHandler
    public void onShowSkillSchoolsView(ShowSkillSchoolsViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowSpellSchoolsView(ShowSpellSchoolsViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowMerchantLocationsView(ShowMerchantsLocationsViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowBuildingRacesView(ShowBuildingRacesViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowUnitRacesView(ShowUnitsRacesViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowItemTypesView(ShowItemTypesViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }
}
