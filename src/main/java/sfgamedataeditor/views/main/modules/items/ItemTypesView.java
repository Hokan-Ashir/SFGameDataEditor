package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.armor.ArmorTypeListView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousListView;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponsTypesListView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ItemTypesView extends AbstractModulesView {

    public ItemTypesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "itemTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.weapons"), WeaponsTypesListView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor"), ArmorTypeListView.class);
//        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.spellScrolls"), SpellScrollsListView.class);
//        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.runes"), RuneRacesListView.class);
//        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.buildingPlans"), BuildingPlansListView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.miscellaneous"), MiscellaneousListView.class);
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return ItemTypesController.class;
    }
}
