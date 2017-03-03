package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

public class SpellScrollsPresenter extends AbstractModulesPresenter<ModuleParameter, SpellScrollsListView, SpellScrollsParametersModel> {

    private static final Integer SCROLL_TYPE_ID = Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.scrolls"));
    private final SpellScrollsModelCreator modelCreator = new SpellScrollsModelCreator();

    public SpellScrollsPresenter(SpellScrollsListView view) {
        super(view);
    }

    @Override
    protected SpellScrollsParametersModel createModel() {
        Integer lowestScrollLevel = getLowestScrollLevel(getView().getSelectedModuleName());
        String selectedSpellScroll = getView().getSelectedModuleName()
                + " - "
                + I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "level")
                + " "
                + lowestScrollLevel;
        int itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(selectedSpellScroll, SCROLL_TYPE_ID);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }

    private Integer getLowestScrollLevel(String scrollBaseName) {
        Set<Integer> scrollLevels = new TreeSet<>();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEMS);
        String prefix = scrollBaseName + " - ";
        for (String key : bundle.keySet()) {
            String value = bundle.getString(key);
            if (value.startsWith(prefix)) {
                scrollLevels.add(Integer.valueOf(value.split("\\s")[3]));
            }
        }

        return scrollLevels.iterator().next();
    }
}
