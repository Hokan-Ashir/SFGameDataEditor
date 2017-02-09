package sfgamedataeditor.views.main.modules.merchants.inventory.items.models;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsObject;
import sfgamedataeditor.database.items.spelleffect.ItemSpellEffectsTableService;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModelParameter;

import javax.swing.*;
import java.util.List;

public class SpellScrollsModelCreator implements ModelCreator<SpellScrollsParametersModel> {
    @Override
    public SpellScrollsParametersModel createModel(int itemId, Icon icon) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        List<ItemSpellEffectsObject> itemSpellEffectsObjects = ItemSpellEffectsTableService.INSTANCE.getObjectsByItemId(itemId);
        SpellScrollsParametersModelParameter parameter = new SpellScrollsParametersModelParameter(itemPriceObject, itemSpellEffectsObjects, icon);
        return new SpellScrollsParametersModel(parameter);
    }
}
