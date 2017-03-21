package sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters;

import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.mvc.objects.IconableParameter;

import javax.swing.*;
import java.util.List;

public class ArmorParametersModelParameter extends IconableParameter {
    private final ItemPriceParametersObject priceParametersObject;
    private final ArmorParametersObject armorParametersObject;
    private final List<ItemRequirementsObject> requirementsObjects;

    public ArmorParametersModelParameter(ItemPriceParametersObject priceParametersObject,
                                         ArmorParametersObject armorParametersObject,
                                         List<ItemRequirementsObject> requirementsObjects,
                                         Icon icon) {
        super(icon);
        this.priceParametersObject = priceParametersObject;
        this.armorParametersObject = armorParametersObject;
        this.requirementsObjects = requirementsObjects;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }

    public ArmorParametersObject getArmorParametersObject() {
        return armorParametersObject;
    }

    public List<ItemRequirementsObject> getRequirementsObjects() {
        return requirementsObjects;
    }
}
