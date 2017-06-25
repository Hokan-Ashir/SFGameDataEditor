package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;

public class UnitRacesView extends AbstractModulesView {

    public UnitRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
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

        addMappings(mappings, UnitListView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitRacesPresenter.class;
    }
}
