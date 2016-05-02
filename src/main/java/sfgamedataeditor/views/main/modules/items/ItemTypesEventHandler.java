package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.views.main.modules.items.armor.ArmorTypesMetaEvent;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingPlansMetaEvent;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousMetaEvent;
import sfgamedataeditor.views.main.modules.items.runes.RuneRacesMetaEvent;
import sfgamedataeditor.views.main.modules.items.spellscrolls.SpellScrollsMetaEvent;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponTypesMetaEvent;

public class ItemTypesEventHandler {

    @EventHandler
    public void onShowArmorListView(ArmorTypesMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowBuildingPlansView(BuildingPlansMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowMiscellaneousView(MiscellaneousMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowRuneRacesView(RuneRacesMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowSpellScrollsView(SpellScrollsMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowWeaponsListView(WeaponTypesMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

}
