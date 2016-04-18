package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.views.main.modules.buildings.ShowBuildingRacesViewEvent;
import sfgamedataeditor.views.main.modules.items.armor.ShowArmorTypeListViewEvent;
import sfgamedataeditor.views.main.modules.items.miscellaneous.ShowMiscellaneousViewEvent;
import sfgamedataeditor.views.main.modules.items.runes.ShowRuneRacesViewEvent;
import sfgamedataeditor.views.main.modules.items.spellscrolls.ShowSpellScrollsViewEvent;
import sfgamedataeditor.views.main.modules.items.weapons.ShowWeaponTypesListViewEvent;

public class ItemTypesEventHandler {

    @EventHandler
    public void onShowArmorListView(ShowArmorTypeListViewEvent event) {
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
    public void onShowWeaponsListView(ShowWeaponTypesListViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

}
