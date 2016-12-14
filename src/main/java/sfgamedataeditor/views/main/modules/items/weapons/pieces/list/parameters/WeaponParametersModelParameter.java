package sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;

public class WeaponParametersModelParameter {
    private final ItemPriceParametersObject priceParametersObject;
    private final ItemSpellEffectsObject itemSpellEffectsObject;
    private final WeaponParametersObject weaponParametersObject;
    private final ItemRequirementsObject requirementsObject;

    public WeaponParametersModelParameter(ItemPriceParametersObject priceParametersObject,
                                          ItemSpellEffectsObject itemSpellEffectsObject,
                                          WeaponParametersObject weaponParametersObject,
                                          ItemRequirementsObject requirementsObject) {
        this.priceParametersObject = priceParametersObject;
        this.itemSpellEffectsObject = itemSpellEffectsObject;
        this.weaponParametersObject = weaponParametersObject;
        this.requirementsObject = requirementsObject;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }

    public ItemSpellEffectsObject getItemSpellEffectsObject() {
        return itemSpellEffectsObject;
    }

    public WeaponParametersObject getWeaponParametersObject() {
        return weaponParametersObject;
    }

    public ItemRequirementsObject getRequirementsObject() {
        return requirementsObject;
    }
}
