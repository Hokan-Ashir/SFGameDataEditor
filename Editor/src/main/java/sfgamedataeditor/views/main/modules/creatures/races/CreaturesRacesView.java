package sfgamedataeditor.views.main.modules.creatures.races;

import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.managers.ModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;

public class CreaturesRacesView extends AbstractModulesView {

    public CreaturesRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    protected void fillSubViewsMappings() {
        Set<String> listOfCreatureRaces = CreatureParametersTableService.INSTANCE.getListOfCreatureRaces();
        addMappings(listOfCreatureRaces, CreaturesView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return CreaturesRacesPresenter.class;
    }

    @Override
    protected Class<? extends ModulePanelManager> getModulesPanelManagerClass() {
        return NameModulesPanelManager.class;
    }
}
