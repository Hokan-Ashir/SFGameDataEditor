package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.views.main.modules.buildings.ShowBuildingRacesViewEvent;
import sfgamedataeditor.views.main.modules.items.armor.ShowArmorListViewEvent;
import sfgamedataeditor.views.main.modules.items.miscellaneous.ShowMiscellaneousViewEvent;
import sfgamedataeditor.views.main.modules.items.runes.ShowRuneRacesViewEvent;
import sfgamedataeditor.views.main.modules.items.spellscrolls.ShowSpellScrollsViewEvent;
import sfgamedataeditor.views.main.modules.items.weapons.ShowWeaponListViewEvent;

public class ItemTypesEventHandler {

    @EventHandler
    public void onShowArmorListView(ShowArmorListViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowBuildingRacesView(ShowBuildingRacesViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowMiscellaneousView(ShowMiscellaneousViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowRuneRacesView(ShowRuneRacesViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowSpellScrollsView(ShowSpellScrollsViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowWeaponsListView(ShowWeaponListViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

}
