package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModelParameter;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellScrollsModelCreator implements ModelCreator<SpellScrollsParametersModel> {
    @Override
    public SpellScrollsParametersModel createModel(int objectId) {
        ItemPriceParametersObject objectByItemId = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(objectId);
        ObjectTuple objectTuple = TextTableService.INSTANCE.getObjectTuple(objectByItemId.nameId, objectByItemId.itemId);
        String scrollName = objectTuple.getName();
        String baseScrollName = scrollName.replaceAll(SpellScrollsListView.SPELL_NAME_REG_EXP, "");
        Map<Integer, Pair<Integer, Integer>> levelToItemsIdMap = getLevelToItemsIdMap(baseScrollName);
        String scrollLevelString = scrollName.replaceAll("\\D+", "");
        Integer scrollLevel = null;
        if (!scrollLevelString.isEmpty()) {
            scrollLevel = Integer.valueOf(scrollLevelString);
        }
        Icon icon = ImageIconsCache.INSTANCE.getImageIcon(getIconPath(), objectId);
        SpellScrollsParametersModelParameter parameter = new SpellScrollsParametersModelParameter(baseScrollName, scrollLevel, levelToItemsIdMap, icon);
        return new SpellScrollsParametersModel(parameter);
    }

    @Override
    public String getIconPath() {
        return "/images/spells_and_scrolls/";
    }

    private Map<Integer, Pair<Integer, Integer>> getLevelToItemsIdMap(String baseScrollName) {
        Map<Integer, Pair<Integer, Integer>> result = new HashMap<>();
        int spellsType = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.spells"));
        List<ObjectTuple> mappings = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(spellsType);

        for (ObjectTuple mapping : mappings) {
            String name = mapping.getName();
            if (!name.contains(baseScrollName)) {
                continue;
            }

            String levelString = name.replaceAll("\\D+", "");
            if (levelString.isEmpty()) {
                result.put(null, new Pair<Integer, Integer>(mapping.getObjectId(), null));
                continue;
            }

            Integer level = Integer.valueOf(levelString);
            result.put(level, new Pair<Integer, Integer>(mapping.getObjectId(), null));
        }

        int scrollsType = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.scrolls"));
        mappings = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(scrollsType);

        for (ObjectTuple mapping : mappings) {
            String name = mapping.getName();
            if (!name.contains(baseScrollName)) {
                continue;
            }

            String levelString = name.replaceAll("\\D+", "");
            if (levelString.isEmpty()) {
                Pair<Integer, Integer> pair = result.get(null);
                result.put(null, new Pair<>(pair.getKey(), mapping.getObjectId()));
                continue;
            }

            Integer level = Integer.valueOf(levelString);
            Pair<Integer, Integer> pair = result.get(level);
            if (pair == null) {
                result.put(level, new Pair<Integer, Integer>(null, mapping.getObjectId()));
            } else {
                result.put(level, new Pair<>(pair.getKey(), mapping.getObjectId()));
            }
        }

        return result;
    }
}
