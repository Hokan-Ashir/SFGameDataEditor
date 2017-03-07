package sfgamedataeditor.views.main.modules.items.herorunes;

import sfgamedataeditor.database.creatures.herospells.HeroSpellObject;
import sfgamedataeditor.database.creatures.herospells.HeroSpellTableService;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.database.creatures.skills.CreatureSkillObject;
import sfgamedataeditor.database.creatures.skills.CreatureSkillTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.herorunes.parameters.HeroesRunesParametersModel;
import sfgamedataeditor.views.main.modules.items.herorunes.parameters.HeroesRunesParametersModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

public class HeroesRunesListPresenter extends AbstractModulesPresenter<ModuleParameter, HeroesRunesListView, HeroesRunesParametersModel> {

    private static final Integer HERO_RUNE_TYPE_ID = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.rune.hero.in.inventory"));

    public HeroesRunesListPresenter(HeroesRunesListView view) {
        super(view);
    }

    @Override
    protected HeroesRunesParametersModel createModel() {
        String selectedRuneName = getView().getSelectedModuleName();
        Integer itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(selectedRuneName, HERO_RUNE_TYPE_ID);
        ItemPriceParametersObject priceParametersObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        CreatureParameterObject creatureParameterObject = CreatureParametersTableService.INSTANCE.getCreatureObjectByStatsId(priceParametersObject.unitStatsId);
        List<CreatureSkillObject> creatureSkills = CreatureSkillTableService.INSTANCE.getCreatureSkillsByStatsId(priceParametersObject.unitStatsId);
        List<HeroSpellObject> heroSpellObjects = HeroSpellTableService.INSTANCE.getHeroSpellsByCreatureId(priceParametersObject.unitStatsId);
        Icon icon = getView().getSelectedModuleIcon();
        HeroesRunesParametersModelParameter parameter = new HeroesRunesParametersModelParameter(priceParametersObject, creatureParameterObject, creatureSkills, heroSpellObjects, icon);
        return new HeroesRunesParametersModel(parameter);
    }
}
