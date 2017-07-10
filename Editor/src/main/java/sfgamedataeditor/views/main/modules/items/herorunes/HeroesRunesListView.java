package sfgamedataeditor.views.main.modules.items.herorunes;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.managers.AbstractModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.herorunes.parameters.HeroesRunesParametersView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class HeroesRunesListView extends AbstractModulesView {

    private static final Integer HERO_RUNE_TYPE_ID = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.rune.hero.in.inventory"));

    public HeroesRunesListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.heroes.runes"));
    }

    @Override
    public void fillSubViewsMappings() {
        List<ObjectTuple> mappings = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(HERO_RUNE_TYPE_ID);
        addMappings(mappings, HeroesRunesParametersView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return HeroesRunesListPresenter.class;
    }

    @Override
    protected Class<? extends AbstractModulePanelManager> getModulesPanelManagerClass() {
        return NameModulesPanelManager.class;
    }

    @Override
    protected String getIconPath() {
        return "/images/heroes/";
    }
}
