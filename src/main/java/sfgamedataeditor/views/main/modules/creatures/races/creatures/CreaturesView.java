package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class CreaturesView extends AbstractModulesView {

    public CreaturesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "moduleName"));
    }

    @Override
    protected void fillComboBoxMapping() {

    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return CreaturesPresenter.class;
    }
}
