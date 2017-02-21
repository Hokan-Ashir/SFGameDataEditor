package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import i18nbase.objects.I18NObject;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.main.modules.merchants.inventory.items.models.SpellScrollsModelCreator;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SpellScrollsPresenter extends AbstractModulesPresenter<ModuleParameter, SpellScrollsListView, SpellScrollsParametersModel> {

    private final SpellScrollsModelCreator modelCreator = new SpellScrollsModelCreator();

    public SpellScrollsPresenter(SpellScrollsListView view) {
        super(view);
    }

    @Override
    protected SpellScrollsParametersModel createModel() {
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
        List<? extends I18NObject> i18NObjects = I18NService.INSTANCE.getI18NObjects(I18NTypes.ITEMS);
        String prefix = scrollBaseName + " - ";
        for (I18NObject i18NObject : i18NObjects) {
            if (i18NObject.value.startsWith(prefix)) {
                scrollLevels.add(Integer.valueOf(i18NObject.value.split("\\s")[3]));
            }
        }

        return scrollLevels.iterator().next();
    }
}
