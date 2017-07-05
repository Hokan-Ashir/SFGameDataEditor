package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class SpellScrollsModelCreator implements ModelCreator<SpellScrollsParametersModel> {
    @Override
    public SpellScrollsParametersModel createModel(int objectId, Icon icon) {
        ItemPriceParametersObject objectByItemId = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(objectId);
        ObjectTuple objectTuple = TextTableService.INSTANCE.getObjectTuple(objectByItemId.nameId, objectByItemId.itemId);
        // extract level via regExp (\d+)
        // we should get here
        //
        // spellScrollId(IPPTS. some type)
        // spellObjectId (IPPTS another type)
        // all spell levels
        // Integer spellId = SpellNameTableService.INSTANCE.getSpellId(originalScrollName);
        // Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(spellTypeId);

        // TODO FIX
        String fullScrollName = I18NService.INSTANCE.getMessage(I18NTypes.PLAYER_LEVEL_STATS_GUI, String.valueOf(objectId));
        String baseScrollName = fullScrollName.split("\\s")[0];
        Integer scrollLevel = Integer.valueOf(fullScrollName.split("\\s")[3]);
        SpellScrollsParametersModelParameter parameter = new SpellScrollsParametersModelParameter(baseScrollName, scrollLevel, icon);
        return new SpellScrollsParametersModel(parameter);
    }
}
