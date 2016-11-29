package sfgamedataeditor.events.processing.strategies.content.modelcreators;

import sfgamedataeditor.database.creatures.CreatureParameterObject;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModel;

import java.util.ResourceBundle;

public class CreatureRacesFromCreaturesModelCreator implements ModelCreator<ModulesModel, CreaturesModel> {

    private static final String RACES_NAMES_MAPPING_FILE_NAME = "racesNameMapping";
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(RACES_NAMES_MAPPING_FILE_NAME);
    private static final String RACE_I18N_PREFIX = "race";

    @Override
    public ModulesModel createModel(CreaturesModel childModel) {
        // TODO maybe it's important which object to take
        CreatureParameterObject creatureParameterObject = childModel.getParameter().getCreatureParameterObjectList().get(0);
        String nonI18NRaceName = BUNDLE.getString(String.valueOf(creatureParameterObject.raceId));
        String raceName = I18N.INSTANCE.getMessage(RACE_I18N_PREFIX + "." + nonI18NRaceName);
        ModuleParameter parameter = new ModuleParameter(raceName);
        return new ModulesModel(parameter);
    }
}
