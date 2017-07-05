package sfgamedataeditor.views.main.modules.buildings.races;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

public class BuildingRacesView extends AbstractModulesView {

    public BuildingRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    public void fillSubViewsMappings() {
        List<ObjectTuple> mappings = BuildingsTableService.INSTANCE.getBuildingsRacesNames();
        addMappings(mappings, BuildingsView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return BuildingRacesPresenter.class;
    }

    @Override
    protected ImageIcon getPanelImageByObjectId(Integer objectId) {
        String iconPath = "/images/races/" + objectId + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }
}
