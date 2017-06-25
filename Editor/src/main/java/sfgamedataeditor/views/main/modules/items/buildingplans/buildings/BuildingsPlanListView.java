package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class BuildingsPlanListView extends AbstractModulesView {

    public BuildingsPlanListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.BUILDING_GUI, "moduleName"));
    }

    @Override
    public void fillSubViewsMappings() {

    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return BuildingsPlanListPresenter.class;
    }

    @Override
    protected ImageIcon getPanelImageByObjectId(Integer objectId) {
        String iconPath = "/images/buildings/" + objectId + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }
}
