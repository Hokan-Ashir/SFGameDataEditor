package sfgamedataeditor.views.main.modules.merchants.inventory.items.models;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsTableService;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModelParameter;

public class SpellScrollsModelCreator implements ModelCreator<SpellScrollsParametersModel> {
    @Override
    public SpellScrollsParametersModel createModel(int itemId) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        ItemSpellEffectsObject itemSpellEffectsObject = ItemSpellEffectsTableService.INSTANCE.getObjectByItemId(itemId);
        SpellScrollsParametersModelParameter parameter = new SpellScrollsParametersModelParameter(itemPriceObject, itemSpellEffectsObject);
        return new SpellScrollsParametersModel(parameter);
    }
}
