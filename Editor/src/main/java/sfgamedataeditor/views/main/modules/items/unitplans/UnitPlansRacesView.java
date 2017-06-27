package sfgamedataeditor.views.main.modules.items.unitplans;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UnitPlansRacesView extends AbstractModulesView {

    public UnitPlansRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitPlansRacesPresenter.class;
    }

    @Override
    public void fillSubViewsMappings() {
        List<ObjectTuple> mappings = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : UnitRaceToItemPlansMapping.INSTANCE.mappings.entrySet()) {
            mappings.add(new ObjectTuple(entry.getKey(), entry.getValue()));
        }

        addMappings(mappings, UnitsPlanListView.class);
    }
}
