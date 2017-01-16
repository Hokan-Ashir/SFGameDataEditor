package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class UnitRacesView extends AbstractModulesView {

    public UnitRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    protected void fillComboBoxMapping() {
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.humans"), UnitListView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.elves"), UnitListView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dwarves"), UnitListView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.orcs"), UnitListView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.trolls"), UnitListView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dark.elves"), UnitListView.class);

    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitRacesPresenter.class;
    }
}
