package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.Set;

public class SpellScrollsPresenter extends AbstractModulesPresenter<ModuleParameter, SpellScrollsListView, SpellScrollsParametersModel> {

    private static final Integer SPELL_TYPE_ID = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.spells"));
    private final SpellScrollsModelCreator modelCreator = new SpellScrollsModelCreator();

    public SpellScrollsPresenter(SpellScrollsListView view) {
        super(view);
    }

    @Override
    protected SpellScrollsParametersModel createModel() {
        Integer lowestScrollLevel = getLowestScrollLevel(getView().getSelectedModuleObjectId());
        String selectedSpellScroll = getView().getSelectedModuleName()
                + " - "
                + lowestScrollLevel;
        // search for ".*SCROLL_BASE_NAME.*SELECTED_LEVEL.*"
        // ^[^\d]*?????? ????[^\d]*1[^\d]*$
        // inside TTS
        Integer itemId = ItemPriceParametersTableService.INSTANCE.getSpellScrollItemId(getView().getSelectedModuleName(), lowestScrollLevel);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }

    private Integer getLowestScrollLevel(Integer spellTypeId) {
        Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(spellTypeId);
        return spellLevels.iterator().next();
    }
}
