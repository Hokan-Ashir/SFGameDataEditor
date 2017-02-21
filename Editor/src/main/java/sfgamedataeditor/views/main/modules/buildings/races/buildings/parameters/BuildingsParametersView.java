package sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters;

import sfgamedataeditor.common.GUIElement;
import sfgamedataeditor.common.IconElement;
import sfgamedataeditor.common.viewconfigurations.buildings.parameters.GUIElements;
import sfgamedataeditor.database.buildings.army.requirements.BuildingsArmyRequirementsObject;
import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsObject;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;

import javax.swing.*;

public class BuildingsParametersView implements PresentableView {
    private JPanel mainPanel;

    @GUIElement(GUIElementId = GUIElements.HP, DTOColumnNames = "hpAmount", DTOClass = BuildingsObject.class)
    private JPanel hpPanel;

    @GUIElement(GUIElementId = GUIElements.RESOURCE_1, DTOColumnNames = {"resourceId", "resourceAmount"}, DTOClass = BuildingsRequirementsObject.class)
    private JPanel resource1Panel;

    @GUIElement(GUIElementId = GUIElements.RESOURCE_2, DTOColumnNames = {"resourceId", "resourceAmount"}, DTOClass = BuildingsRequirementsObject.class)
    private JPanel resource2Panel;

    @GUIElement(GUIElementId = GUIElements.RESOURCE_3, DTOColumnNames = {"resourceId", "resourceAmount"}, DTOClass = BuildingsRequirementsObject.class)
    private JPanel resource3Panel;

    @GUIElement(GUIElementId = GUIElements.RACE, DTOColumnNames = "raceId", DTOClass = BuildingsObject.class)
    private JPanel racePanel;

    @IconElement
    private JLabel iconLabel;

    @GUIElement(GUIElementId = GUIElements.ARMY_UNIT_1, DTOColumnNames = "unitId", DTOClass = BuildingsArmyRequirementsObject.class)
    private JPanel producingUnit1Panel;

    @GUIElement(GUIElementId = GUIElements.ARMY_UNIT_2, DTOColumnNames = "unitId", DTOClass = BuildingsArmyRequirementsObject.class)
    private JPanel producingUnit2Panel;

    @GUIElement(GUIElementId = GUIElements.ARMY_UNIT_3, DTOColumnNames = "unitId", DTOClass = BuildingsArmyRequirementsObject.class)
    private JPanel producingUnit3Panel;

    @GUIElement(GUIElementId = GUIElements.ARMY_UNIT_4, DTOColumnNames = "unitId", DTOClass = BuildingsArmyRequirementsObject.class)
    private JPanel producingUnit4Panel;

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return BuildingsParametersPresenter.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
