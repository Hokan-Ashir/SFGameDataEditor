package sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;

import javax.swing.*;
import java.util.List;

public class SpellScrollsParametersModelParameter {
    private final ItemPriceParametersObject priceParametersObject;
    private final List<ItemSpellEffectsObject> itemSpellEffectsObjects;
    private final Icon icon;

    public SpellScrollsParametersModelParameter(ItemPriceParametersObject priceParametersObject, List<ItemSpellEffectsObject> itemSpellEffectsObjects, Icon icon) {
        this.priceParametersObject = priceParametersObject;
        this.itemSpellEffectsObjects = itemSpellEffectsObjects;
        this.icon = icon;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }

    public List<ItemSpellEffectsObject> getItemSpellEffectsObjects() {
        return itemSpellEffectsObjects;
    }

    public Icon getIcon() {
        return icon;
    }
}
