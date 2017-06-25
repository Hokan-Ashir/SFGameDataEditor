package sfgamedataeditor.views.common.views;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.SubViewPanelTuple;
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
        List<SubViewPanelTuple> mappings = new ArrayList<SubViewPanelTuple>() {{
           add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.humans")));
           add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.elves")));
           add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dwarves")));
           add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.orcs")));
           add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.trolls")));
           add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dark.elves")));
        }};

        addMappings(mappings, getSubModulesViewClass());
    }

    protected abstract Class<? extends PresentableView> getSubModulesViewClass();
}
