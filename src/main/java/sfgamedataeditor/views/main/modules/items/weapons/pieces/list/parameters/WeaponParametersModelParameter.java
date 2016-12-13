package sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters;

import sfgamedataeditor.database.items.effects.ItemEffectsObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;

public class WeaponParametersModelParameter {
    private final ItemPriceParametersObject priceParametersObject;
    private final ItemEffectsObject itemEffectsObject;
    private final WeaponParametersObject weaponParametersObject;
    private final ItemRequirementsObject requirementsObject;

    public WeaponParametersModelParameter(ItemPriceParametersObject priceParametersObject,
                                          ItemEffectsObject itemEffectsObject,
                                          WeaponParametersObject weaponParametersObject,
                                          ItemRequirementsObject requirementsObject) {
        this.priceParametersObject = priceParametersObject;
        this.itemEffectsObject = itemEffectsObject;
        this.weaponParametersObject = weaponParametersObject;
        this.requirementsObject = requirementsObject;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }

    public ItemEffectsObject getItemEffectsObject() {
        return itemEffectsObject;
    }

    public WeaponParametersObject getWeaponParametersObject() {
        return weaponParametersObject;
    }

    public ItemRequirementsObject getRequirementsObject() {
        return requirementsObject;
    }
}
