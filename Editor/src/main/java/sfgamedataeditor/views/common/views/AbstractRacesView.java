package sfgamedataeditor.views.common.views;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractRacesView extends AbstractModulesView {
    protected AbstractRacesView(String viewName) {
        super(viewName);
    }

    @Override
    public void fillSubViewsMappings() {
        Set<String> mappings = new TreeSet<String>() {{
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.humans"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.elves"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dwarves"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.orcs"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.trolls"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dark.elves"));
        }};

        addMappings(mappings, getSubModulesViewClass());
    }

    protected abstract Class<? extends PresentableView> getSubModulesViewClass();
}
