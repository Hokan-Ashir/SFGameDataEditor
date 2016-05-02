package sfgamedataeditor.views.main.modules.common.modules;

import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.views.main.modules.buildings.BuildingRacesMetaEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesMetaEvent;
import sfgamedataeditor.views.main.modules.merchants.MerchantLocationsMetaEvent;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsMetaEvent;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsMetaEvent;
import sfgamedataeditor.views.main.modules.units.UnitRacesMetaEvent;

public class ModulesEventHandler {

    @EventHandler
    public void onShowSkillSchoolsView(SkillSchoolsMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowSpellSchoolsView(SpellSchoolsMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowMerchantLocationsView(MerchantLocationsMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowBuildingRacesView(BuildingRacesMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowUnitRacesView(UnitRacesMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowItemTypesView(ItemTypesMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }
}
