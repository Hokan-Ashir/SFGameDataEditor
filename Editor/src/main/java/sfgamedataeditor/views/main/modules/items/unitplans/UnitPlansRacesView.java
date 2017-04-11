package sfgamedataeditor.views.main.modules.items.unitplans;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.views.AbstractRacesView;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class UnitPlansRacesView extends AbstractRacesView {

    public UnitPlansRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitPlansRacesPresenter.class;
    }

    @Override
    protected Class<? extends PresentableView> getSubModulesViewClass() {
        return UnitsPlanListView.class;
    }
}
