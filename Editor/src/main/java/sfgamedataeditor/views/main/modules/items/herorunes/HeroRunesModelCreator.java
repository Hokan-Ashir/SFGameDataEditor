package sfgamedataeditor.views.main.modules.items.herorunes;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.creatures.herospells.HeroSpellObject;
import sfgamedataeditor.database.creatures.herospells.HeroSpellTableService;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.database.creatures.skills.CreatureSkillObject;
import sfgamedataeditor.database.creatures.skills.CreatureSkillTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.items.herorunes.parameters.HeroesRunesParametersModel;
import sfgamedataeditor.views.main.modules.items.herorunes.parameters.HeroesRunesParametersModelParameter;

import javax.swing.*;
import java.util.List;

public class HeroRunesModelCreator implements ModelCreator<HeroesRunesParametersModel> {
    @Override
    public HeroesRunesParametersModel createModel(int objectId) {
        ItemPriceParametersObject priceParametersObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(objectId);
        CreatureParameterObject creatureParameterObject = CreatureParametersTableService.INSTANCE.getCreatureObjectByStatsId(priceParametersObject.unitStatsId);
        List<CreatureSkillObject> creatureSkills = CreatureSkillTableService.INSTANCE.getCreatureSkillsByStatsId(priceParametersObject.unitStatsId);
        List<HeroSpellObject> heroSpellObjects = HeroSpellTableService.INSTANCE.getHeroSpellsByCreatureId(priceParametersObject.unitStatsId);
        Icon icon = ImageIconsCache.INSTANCE.getImageIcon(getIconPath(), objectId);
        HeroesRunesParametersModelParameter parameter = new HeroesRunesParametersModelParameter(priceParametersObject, creatureParameterObject, creatureSkills, heroSpellObjects, icon);
        return new HeroesRunesParametersModel(parameter);
    }

    @Override
    public String getIconPath() {
        return "/images/heroes/";
    }
}
