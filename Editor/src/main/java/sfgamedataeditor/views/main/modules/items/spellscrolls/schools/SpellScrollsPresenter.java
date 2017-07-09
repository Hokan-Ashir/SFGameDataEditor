package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

public class SpellScrollsPresenter extends AbstractModulesPresenter<ModuleParameter, SpellScrollsListView, SpellScrollsParametersModel> {

    private final SpellScrollsModelCreator modelCreator = new SpellScrollsModelCreator();

    public SpellScrollsPresenter(SpellScrollsListView view) {
        super(view);
    }

    @Override
    protected SpellScrollsParametersModel createModel() {
        Integer lowestScrollLevel = getLowestScrollLevel();
        Integer scrollTypeId = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.spells"));
        Integer itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByNameAndLevel(getView().getSelectedModuleName(), lowestScrollLevel, scrollTypeId);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }

    private Integer getLowestScrollLevel() {
        int scrollsType = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.spells"));
        List<ObjectTuple> mappings = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(scrollsType);
        String selectedModuleName = getView().getSelectedModuleName();
        Integer lowestLevel = Integer.MAX_VALUE;
        for (ObjectTuple mapping : mappings) {
            String name = mapping.getName();
            if (!name.contains(selectedModuleName)) {
                continue;
            }

            String levelString = name.replaceAll("\\D+", "");
            if (levelString.isEmpty()) {
                continue;
            }

            Integer level = Integer.valueOf(levelString);
            if (level < lowestLevel) {
                lowestLevel = level;
            }
        }

        // spells like Berserk don't have levels
        return lowestLevel == Integer.MAX_VALUE ? null : lowestLevel;
    }
}
