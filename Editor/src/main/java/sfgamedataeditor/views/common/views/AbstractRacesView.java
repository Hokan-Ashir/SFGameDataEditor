package sfgamedataeditor.views.common.views;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRacesView extends AbstractModulesView {

    public AbstractRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    public void fillSubViewsMappings() {
        List<ObjectTuple> mappings = new ArrayList<ObjectTuple>() {{
            add(createTuple("races.humans", 1));
            add(createTuple("races.dwarves", 2));
            add(createTuple( "races.elves", 3));
            add(createTuple("races.trolls", 4));
            add(createTuple("races.orcs", 5));
            add(createTuple("races.dark.elves", 6));
        }};

        addMappings(mappings, BuildingsPlanListView.class);
    }

    protected abstract Class<? extends PresentableView> getPanelsViewClass();

    private ObjectTuple createTuple(String i18nKey, int raceId) {
        return new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, i18nKey), raceId);
    }

    @Override
    protected ImageIcon getPanelImageByObjectId(Integer objectId) {
        String iconPath = "/images/races/" + objectId + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }
}
