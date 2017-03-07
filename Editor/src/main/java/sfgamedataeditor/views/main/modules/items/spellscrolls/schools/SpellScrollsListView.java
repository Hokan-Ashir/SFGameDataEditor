package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.managers.ModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class SpellScrollsListView extends AbstractModulesView {

    public SpellScrollsListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.scrolls"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillSubViewsMappings() {
        int scrollsType = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.scrolls"));
        Set<String> scrollsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(scrollsType);
        scrollsNames = getFilteredScrollNames(scrollsNames);
        addMappings(scrollsNames, SpellScrollsParametersView.class);
    }

    private Set<String> getFilteredScrollNames(Set<String> scrollNames) {
        Set<String> result = new HashSet<>();
        for (String scrollName : scrollNames) {
            String originalScrollName = scrollName.split(" -")[0];
            if (!result.contains(originalScrollName)) {
                result.add(originalScrollName);
            }
        }

        return result;
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SpellScrollsPresenter.class;
    }

    @Override
    protected ImageIcon getPanelImageByPanelName(String panelName) {
        String spellNameKey = ViewTools.getKeyStringByPropertyValue(panelName, I18NTypes.SPELLS_GUI);
        if (spellNameKey == null) {
            return null;
        }

        spellNameKey = spellNameKey.replaceAll("(.*)\\.name", "$1");

        String iconPath = "/images/spells_and_scrolls/" + spellNameKey + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }

    @Override
    protected Class<? extends ModulePanelManager> getModulesPanelManagerClass() {
        return NameModulesPanelManager.class;
    }
}
