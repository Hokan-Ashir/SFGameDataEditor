package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.ObjectTuple;
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
        List<ObjectTuple> mappings = new ArrayList<ObjectTuple>() {{
            add(new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.humans")));
            add(new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.elves")));
            add(new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dwarves")));
            add(new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.orcs")));
            add(new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.trolls")));
            add(new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dark.elves")));
        }};

        addMappings(mappings, UnitListView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitRacesPresenter.class;
    }
}
