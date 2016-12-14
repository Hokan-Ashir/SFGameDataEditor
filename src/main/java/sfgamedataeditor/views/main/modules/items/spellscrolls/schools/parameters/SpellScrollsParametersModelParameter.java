package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;

public class SpellScrollsParametersModelParameter {
    private final ItemPriceParametersObject priceParametersObject;
    private final ItemSpellEffectsObject itemSpellEffectsObject;

    public SpellScrollsParametersModelParameter(ItemPriceParametersObject priceParametersObject, ItemSpellEffectsObject itemSpellEffectsObject) {
        this.priceParametersObject = priceParametersObject;
        this.itemSpellEffectsObject = itemSpellEffectsObject;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }

    public ItemSpellEffectsObject getItemSpellEffectsObject() {
        return itemSpellEffectsObject;
    }
}
