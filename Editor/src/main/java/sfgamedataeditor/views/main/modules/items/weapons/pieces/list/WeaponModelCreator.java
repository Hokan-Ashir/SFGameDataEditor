package sfgamedataeditor.views.main.modules.items.weapons.pieces.list;

import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsTableService;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsTableService;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersModelParameter;

import javax.swing.*;
import java.util.List;

public class WeaponModelCreator implements ModelCreator<WeaponParametersModel> {

    @Override
    public WeaponParametersModel createModel(int objectId, Icon icon) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(objectId);
        List<ItemSpellEffectsObject> itemEffectsObjects = ItemSpellEffectsTableService.INSTANCE.getObjectsByItemId(objectId);
        WeaponParametersObject weaponParametersObject = WeaponParametersTableService.INSTANCE.getObjectByItemId(objectId);
        List<ItemRequirementsObject> requirementsObjects = ItemRequirementsTableService.INSTANCE.getObjectsByItemId(objectId);
        ArmorParametersObject armorParametersObject = ArmorParametersTableService.INSTANCE.getObjectByItemId(objectId);
        WeaponParametersModelParameter parameter = new WeaponParametersModelParameter(itemPriceObject, itemEffectsObjects, weaponParametersObject, armorParametersObject, requirementsObjects, icon);
        return new WeaponParametersModel(parameter);
    }
}
