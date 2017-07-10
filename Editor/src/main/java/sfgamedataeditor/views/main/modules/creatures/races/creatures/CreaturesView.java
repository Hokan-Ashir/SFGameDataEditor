package sfgamedataeditor.views.main.modules.creatures.races.creatures;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.managers.AbstractModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class CreaturesView extends AbstractModulesView {

    public CreaturesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "moduleName"));
    }

    @Override
    public void fillSubViewsMappings() {

    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return CreaturesPresenter.class;
    }

    @Override
    protected Class<? extends AbstractModulePanelManager> getModulesPanelManagerClass() {
        return NameModulesPanelManager.class;
    }

    @Override
    protected String getIconPath() {
        return "/images/creatures/";
    }

    @Override
    public void localize() {
        
    }
}
