package sfgamedataeditor.views.main.modules.items.runes;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.notimplemented.NotImplementedView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;
import java.util.TreeSet;

public class RuneRacesListView extends AbstractModulesView {

    public RuneRacesListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    protected void fillSubViewsMappings() {
        Set<String> mappings = new TreeSet<String>() {{
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.humans"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.elves"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dwarves"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.orcs"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.trolls"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dark.elves"));
        }};

        addMappings(mappings, NotImplementedView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return null;
    }
}
