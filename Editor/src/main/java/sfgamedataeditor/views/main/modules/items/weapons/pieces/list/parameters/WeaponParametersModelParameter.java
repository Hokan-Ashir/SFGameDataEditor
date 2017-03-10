package sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.mvc.objects.IconableParameter;

import javax.swing.*;
import java.util.List;

public class WeaponParametersModelParameter extends IconableParameter {
    private final ItemPriceParametersObject priceParametersObject;
    private final List<ItemSpellEffectsObject> itemSpellEffectsObjects;
    private final WeaponParametersObject weaponParametersObject;
    private final List<ItemRequirementsObject> requirementsObjects;

    public WeaponParametersModelParameter(ItemPriceParametersObject priceParametersObject,
                                          List<ItemSpellEffectsObject> itemSpellEffectsObjects,
                                          WeaponParametersObject weaponParametersObject,
                                          List<ItemRequirementsObject> requirementsObjects,
                                          Icon icon) {
        super(icon);
        this.priceParametersObject = priceParametersObject;
        this.itemSpellEffectsObjects = itemSpellEffectsObjects;
        this.weaponParametersObject = weaponParametersObject;
        this.requirementsObjects = requirementsObjects;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }

    public List<ItemSpellEffectsObject> getItemSpellEffectsObjects() {
        return itemSpellEffectsObjects;
    }

    public WeaponParametersObject getWeaponParametersObject() {
        return weaponParametersObject;
    }

    public List<ItemRequirementsObject> getRequirementsObjects() {
        return requirementsObjects;
    }
}
