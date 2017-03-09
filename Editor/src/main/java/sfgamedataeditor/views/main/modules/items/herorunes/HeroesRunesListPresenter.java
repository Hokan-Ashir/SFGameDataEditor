package sfgamedataeditor.views.main.modules.items.herorunes;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.herorunes.parameters.HeroesRunesParametersModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class HeroesRunesListPresenter extends AbstractModulesPresenter<ModuleParameter, HeroesRunesListView, HeroesRunesParametersModel> {

    private static final Integer HERO_RUNE_TYPE_ID = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.rune.hero.in.inventory"));
    private final HeroRunesModelCreator modelCreator = new HeroRunesModelCreator();

    public HeroesRunesListPresenter(HeroesRunesListView view) {
        super(view);
    }

    @Override
    protected HeroesRunesParametersModel createModel() {
        String selectedRuneName = getView().getSelectedModuleName();
        Integer itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(selectedRuneName, HERO_RUNE_TYPE_ID);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }
}
