package sfgamedataeditor.views.main.modules.spellbook;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.managers.ModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.main.modules.spellbook.parameters.SpellBookParametersView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class SpellBookListView extends AbstractModulesView {

    private static final Logger LOGGER = Logger.getLogger(SpellBookListView.class);

    public SpellBookListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "spells"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillSubViewsMappings() {
        int scrollsType = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_PIECES_NAME_MAPPING, "items.spells"));
        Set<String> scrollsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(scrollsType);
        scrollsNames = getFilteredScrollNames(scrollsNames);
        addMappings(scrollsNames, SpellBookParametersView.class);
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
        return SpellBookPresenter.class;
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
