package sfgamedataeditor.views.main.modules.merchants;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.cache.ImageIconsCache;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.managers.ModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.main.modules.merchants.inventory.MerchantInventoryView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.Set;

public class MerchantsView extends AbstractModulesView {

    private static final Integer MERCHANTS_RACE_ID = 168;
    private static final Logger LOGGER = Logger.getLogger(MerchantsView.class);

    public MerchantsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "merchantLocations"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillSubViewsMappings() {
        Set<String> creatureNames = CreatureCommonParametersTableService.INSTANCE.getCreatureNamesByRaceId(MERCHANTS_RACE_ID);
        addMappings(creatureNames, MerchantInventoryView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return MerchantsPresenter.class;
    }

    @Override
    protected ImageIcon getPanelImageByPanelName(String panelName) {
        String merchantNameKey = ViewTools.getKeyStringByPropertyValue(panelName, I18NTypes.CREATURES);
        if (merchantNameKey == null) {
            return null;
        }

        String iconPath = "/images/merchants/" + merchantNameKey + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }

    @Override
    protected Class<? extends ModulePanelManager> getModulesPanelManagerClass() {
        return NameModulesPanelManager.class;
    }
}
