package sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters;

import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.effects.ItemEffectsObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;

public class ArmorParametersModelParameter {
    private final ItemPriceParametersObject priceParametersObject;
    private final ItemEffectsObject itemEffectsObject;
    private final ArmorParametersObject armorParametersObject;
    private final ItemRequirementsObject requirementsObject;

    public ArmorParametersModelParameter(ItemPriceParametersObject priceParametersObject,
                                         ItemEffectsObject itemEffectsObject,
                                         ArmorParametersObject armorParametersObject,
                                         ItemRequirementsObject requirementsObject) {
        this.priceParametersObject = priceParametersObject;
        this.itemEffectsObject = itemEffectsObject;
        this.armorParametersObject = armorParametersObject;
        this.requirementsObject = requirementsObject;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }

    public ItemEffectsObject getItemEffectsObject() {
        return itemEffectsObject;
    }

    public ArmorParametersObject getArmorParametersObject() {
        return armorParametersObject;
    }

    public ItemRequirementsObject getRequirementsObject() {
        return requirementsObject;
    }
}
