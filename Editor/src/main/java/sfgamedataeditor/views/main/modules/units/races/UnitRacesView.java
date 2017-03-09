package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;
import java.util.TreeSet;

public class UnitRacesView extends AbstractModulesView {

    public UnitRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    protected void fillSubViewsMappings() {
        Set<String> names = new TreeSet<>();
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.humans"));
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.elves"));
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dwarves"));
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.orcs"));
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.trolls"));
        names.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dark.elves"));

        addMappings(names, UnitListView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitRacesPresenter.class;
    }
}
