package sfgamedataeditor.views.main.modules.items.herorunes.parameters;

import sfgamedataeditor.database.creatures.herospells.HeroSpellObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.skills.CreatureSkillObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;

import javax.swing.*;
import java.util.List;

public class HeroesRunesParametersModelParameter {
    private final ItemPriceParametersObject priceParametersObject;
    private final CreatureParameterObject creatureParameterObject;
    private final List<CreatureSkillObject> creatureSkills;
    private final List<HeroSpellObject> heroSpellObjects;
    private final Icon icon;

    public HeroesRunesParametersModelParameter(ItemPriceParametersObject priceParametersObject,
                                               CreatureParameterObject creatureParameterObject,
                                               List<CreatureSkillObject> creatureSkills,
                                               List<HeroSpellObject> heroSpellObjects,
                                               Icon icon) {
        this.priceParametersObject = priceParametersObject;
        this.creatureParameterObject = creatureParameterObject;
        this.creatureSkills = creatureSkills;
        this.heroSpellObjects = heroSpellObjects;
        this.icon = icon;
    }

    public List<HeroSpellObject> getHeroSpellObjects() {
        return heroSpellObjects;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }

    public CreatureParameterObject getCreatureParameterObject() {
        return creatureParameterObject;
    }

    public List<CreatureSkillObject> getCreatureSkills() {
        return creatureSkills;
    }

    public Icon getIcon() {
        return icon;
    }
}
