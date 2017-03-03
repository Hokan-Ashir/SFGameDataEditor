package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class BuildingsPlanListView extends AbstractModulesView {

    private final Map<String, String> buildingRaceTypeToNameMapping = new HashMap<>();

    public BuildingsPlanListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.BUILDING_GUI, "moduleName"));
        initializeBuildingRaceTypeToNameMapping();
    }

    private void initializeBuildingRaceTypeToNameMapping() {
        addMapping(buildingRaceTypeToNameMapping, "races.humans", "items.building.plan.in.inventory.humans");
        addMapping(buildingRaceTypeToNameMapping, "races.elves", "items.building.plan.in.inventory.elves");
        addMapping(buildingRaceTypeToNameMapping, "races.dwarves", "items.building.plan.in.inventory.dwarves");
        addMapping(buildingRaceTypeToNameMapping, "races.orcs", "items.building.plan.in.inventory.orcs");
        addMapping(buildingRaceTypeToNameMapping, "races.trolls", "items.building.plan.in.inventory.trolls");
        addMapping(buildingRaceTypeToNameMapping, "races.dark.elves", "items.building.plan.in.inventory.dark.elves");
    }

    private void addMapping(Map<String, String> map, String raceI18nKey, String itemTypeI18nKey) {
        map.put(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeI18nKey), I18NService.INSTANCE.getMessage(I18NTypes.COMMON, raceI18nKey));
    }

    @Override
    protected void fillSubViewsMappings() {

    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return BuildingsPlanListPresenter.class;
    }

    @Override
    protected ImageIcon getPanelImageByPanelName(String panelName) {
        BuildingsPlanListPresenter presenter = ViewRegister.INSTANCE.getPresenter(getClass());
        Integer buildingsRaceType = presenter.getModel().getParameter().getBuildingsRaceType();
        String raceName = buildingRaceTypeToNameMapping.get(String.valueOf(buildingsRaceType));
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
