package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;

import java.util.List;

public class SpellScrollsParametersModelParameter {
    private final ItemPriceParametersObject priceParametersObject;
    private final List<ItemSpellEffectsObject> itemSpellEffectsObjects;

    public SpellScrollsParametersModelParameter(ItemPriceParametersObject priceParametersObject, List<ItemSpellEffectsObject> itemSpellEffectsObjects) {
        this.priceParametersObject = priceParametersObject;
        this.itemSpellEffectsObjects = itemSpellEffectsObjects;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }

    public List<ItemSpellEffectsObject> getItemSpellEffectsObjects() {
        return itemSpellEffectsObjects;
    }
}
