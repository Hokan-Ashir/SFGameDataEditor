package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.merchants.inventory.MerchantInventoryTableService;
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

    public MerchantsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "merchantLocations"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillSubViewsMappings() {
        Set<String> creatureNames = MerchantInventoryTableService.INSTANCE.getAllMerchantNames();
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
