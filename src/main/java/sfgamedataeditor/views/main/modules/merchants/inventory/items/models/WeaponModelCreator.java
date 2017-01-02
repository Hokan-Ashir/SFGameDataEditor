package sfgamedataeditor.views.main.modules.merchants.inventory.items.models;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsTableService;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsTableService;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersModelParameter;

import java.util.List;

public class WeaponModelCreator implements ModelCreator<WeaponParametersModel> {

    @Override
    public WeaponParametersModel createModel(int itemId) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        List<ItemSpellEffectsObject> itemEffectsObjects = ItemSpellEffectsTableService.INSTANCE.getObjectsByItemId(itemId);
        WeaponParametersObject weaponParametersObject = WeaponParametersTableService.INSTANCE.getObjectByItemId(itemId);
        List<ItemRequirementsObject> requirementsObjects = ItemRequirementsTableService.INSTANCE.getObjectsByItemId(itemId);
        WeaponParametersModelParameter parameter = new WeaponParametersModelParameter(itemPriceObject, itemEffectsObjects, weaponParametersObject, requirementsObjects);
        return new WeaponParametersModel(parameter);
    }
}
