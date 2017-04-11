package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingPlansMapping;
import sfgamedataeditor.views.utility.ViewTools;
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
    protected ImageIcon getPanelImageByPanelName(String panelName) {
        BuildingsPlanListPresenter presenter = ViewRegister.INSTANCE.getPresenter(getClass());
        Integer buildingsRaceType = presenter.getModel().getParameter().getBuildingsRaceType();
        String raceName = BuildingPlansMapping.INSTANCE.getRaceMappingValue(String.valueOf(buildingsRaceType));
        Integer buildingId;
        try {
            buildingId = ViewTools.getKeyByPropertyValue(panelName, I18NTypes.BUILDING_NAMES_MAPPING);
        } catch (NumberFormatException e) {
            try {
                buildingId = ViewTools.getKeyByPropertyValue(panelName + " (" + raceName + ")", I18NTypes.BUILDING_NAMES_MAPPING);
            } catch (NumberFormatException e1) {
                // in case some unknown items that has to be scrolls for some buildings
                return null;
            }
        }
        String iconPath = "/images/buildings/" + buildingId + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }
}
