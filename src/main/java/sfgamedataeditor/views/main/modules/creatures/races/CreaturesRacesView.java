package sfgamedataeditor.views.main.modules.creatures.races;

import sfgamedataeditor.database.creatures.CreatureParametersTableService;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesView;

import java.util.Set;

public class CreaturesRacesView extends AbstractRacesView {

    @Override
    protected void fillComboBoxMapping() {
        Set<String> listOfCreatureRaces = CreatureParametersTableService.INSTANCE.getListOfCreatureRaces();
        for (String creatureRace : listOfCreatureRaces) {
            addMapping(creatureRace, CreaturesView.class);
        }
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return CreaturesRacesController.class;
    }
}
