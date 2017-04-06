package sfgamedataeditor.views.main.modules.buildings.races.buildings;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class BuildingsView extends AbstractModulesView {

    public BuildingsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.BUILDING_GUI, "moduleName"));
    }

    @Override
    public void fillSubViewsMappings() {

    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return BuildingsPresenter.class;
    }

    @Override
    protected ImageIcon getPanelImageByPanelName(String panelName) {
        Integer buildingId = ViewTools.getKeyByPropertyValue(panelName, I18NTypes.BUILDING_NAMES_MAPPING);
        String iconPath = "/images/buildings/" + buildingId + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }
}
