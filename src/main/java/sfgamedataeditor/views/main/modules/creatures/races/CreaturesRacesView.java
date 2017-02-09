package sfgamedataeditor.views.main.modules.creatures.races;

import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.managers.ModulePanelManager;
import sfgamedataeditor.views.common.managers.NameModulesPanelManager;
import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesView;

import java.util.Set;

public class CreaturesRacesView extends AbstractRacesView {

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
