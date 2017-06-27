package sfgamedataeditor.views.common.views;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRacesView extends AbstractModulesView {
    protected AbstractRacesView(String viewName) {
        super(viewName);
    }

    @Override
    public void fillSubViewsMappings() {
        List<ObjectTuple> mappings = new ArrayList<ObjectTuple>() {{
           add(createTuple("races.humans", 1));
           add(createTuple( "races.elves", 2));
           add(createTuple("races.dwarves", 3));
           add(createTuple("races.orcs", 4));
           add(createTuple("races.trolls", 5));
           add(createTuple("races.dark.elves", 6));
        }};

        addMappings(mappings, getSubModulesViewClass());
    }

    private ObjectTuple createTuple(String i18nKey, int raceId) {
        return new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, i18nKey), raceId);
    }

    protected abstract Class<? extends PresentableView> getSubModulesViewClass();
}
