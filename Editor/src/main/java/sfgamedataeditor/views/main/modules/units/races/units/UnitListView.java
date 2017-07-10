package sfgamedataeditor.views.main.modules.units.races.units;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class UnitListView extends AbstractModulesView {

    public UnitListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "units"));
    }

    @Override
    public void fillSubViewsMappings() {

    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitListPresenter.class;
    }

    @Override
    protected String getIconPath() {
        return "/images/units/";
    }

    @Override
    public void localize() {

    }
}
