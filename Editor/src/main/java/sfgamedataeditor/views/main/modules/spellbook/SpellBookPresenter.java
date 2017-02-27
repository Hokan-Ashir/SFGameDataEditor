package sfgamedataeditor.views.main.modules.spellbook;

import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.merchants.inventory.items.models.SpellBookModelCreator;
import sfgamedataeditor.views.main.modules.spellbook.parameters.SpellBookParametersModel;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

public class SpellBookPresenter extends AbstractModulesPresenter<ModuleParameter, SpellBookListView, SpellBookParametersModel> {

    private final SpellBookModelCreator modelCreator = new SpellBookModelCreator();

    public SpellBookPresenter(SpellBookListView view) {
        super(view);
    }

    @Override
    protected SpellBookParametersModel createModel() {
        int itemId;
        Integer lowestScrollLevel = getLowestScrollLevel(getView().getSelectedModuleValue());
        String selectedSpellScroll = getView().getSelectedModuleValue()
                + " - "
                + I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "level")
                + " "
                + lowestScrollLevel;
        itemId = ViewTools.getKeyByPropertyValue(selectedSpellScroll, I18NTypes.ITEMS);
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
