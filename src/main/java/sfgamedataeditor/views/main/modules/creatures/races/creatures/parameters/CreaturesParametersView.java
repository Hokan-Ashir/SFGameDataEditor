package sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.viewconfigurations.creatureparameters.GUIElements;
import sfgamedataeditor.database.creatures.CreatureParameterObject;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;

import javax.swing.*;

public class CreaturesParametersView implements ControllableView {

    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.NAME, DTOColumnNames = "name", DTOClass = CreatureParameterObject.class)
    private JPanel name;

    @GUIElement(GUIElementId = GUIElements.STATS_ID, DTOColumnNames = "statsId", DTOClass = CreatureParameterObject.class)
    private JPanel statsId;

    @GUIElement(GUIElementId = GUIElements.LEVEL, DTOColumnNames = "level", DTOClass = CreatureParameterObject.class)
    private JPanel level;

    @GUIElement(GUIElementId = GUIElements.RACE_ID, DTOColumnNames = "raceId", DTOClass = CreatureParameterObject.class)
    private JPanel raceId;

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return CreaturesParametersController.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
