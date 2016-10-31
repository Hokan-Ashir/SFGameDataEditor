package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.items.armor.ArmorTypeListView;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingPlansListView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousListView;
import sfgamedataeditor.views.main.modules.items.runes.RuneRacesListView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.SpellScrollsListView;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponsTypesListView;

public class ItemTypesView extends AbstractModulesView {

    public ItemTypesView() {
        super(I18N.INSTANCE.getMessage("itemTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addMapping(I18N.INSTANCE.getMessage("items.weapons"), WeaponsTypesListView.class);
        addMapping(I18N.INSTANCE.getMessage("items.armor"), ArmorTypeListView.class);
        addMapping(I18N.INSTANCE.getMessage("items.spellScrolls"), SpellScrollsListView.class);
        addMapping(I18N.INSTANCE.getMessage("items.runes"), RuneRacesListView.class);
        addMapping(I18N.INSTANCE.getMessage("items.buildingPlans"), BuildingPlansListView.class);
        addMapping(I18N.INSTANCE.getMessage("items.miscellaneous"), MiscellaneousListView.class);
    }

    @Override
    public void render() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideNavigationPanel(this);
    }

    @Override
    public void unrender() {

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return null;
    }
}
