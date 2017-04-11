package sfgamedataeditor.views.main.modules.items.workersrunes;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.views.AbstractRacesView;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class WorkersRuneRacesView extends AbstractRacesView {

    public WorkersRuneRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    protected Class<? extends PresentableView> getSubModulesViewClass() {
        return WorkersRunesParametersView.class;
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return WorkersRuneRacesPresenter.class;
    }
}
