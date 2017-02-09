package sfgamedataeditor.views.main.modules.items.spellscrolls.schools;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.managers.ModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

public class SpellScrollsListView extends AbstractModulesView {

    private static final Logger LOGGER = Logger.getLogger(SpellScrollsListView.class);

    public SpellScrollsListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.scrolls"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillSubViewsMappings() {
        int scrollsType = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_PIECES_NAME_MAPPING, "items.scrolls"));
        Set<String> scrollsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(scrollsType);
        addMappings(scrollsNames, SpellScrollsParametersView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SpellScrollsPresenter.class;
    }

    @Override
    protected Image getPanelImageByPanelName(String panelName) {
        panelName = panelName.split(" -")[0];
        String spellNameKey = ViewTools.getKeyStringByPropertyValue(panelName, I18NTypes.SPELLS_GUI);
        if (spellNameKey == null) {
            return null;
        }

        spellNameKey = spellNameKey.replaceAll("(.*)\\.name", "$1");

        try {
            URL resource = getClass().getResource("/images/spells_and_scrolls/" + spellNameKey + ".png");
            if (resource == null) {
                return null;
            }

            return ImageIO.read(resource);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected Class<? extends ModulePanelManager> getModulesPanelManagerClass() {
        return NameModulesPanelManager.class;
    }
}
