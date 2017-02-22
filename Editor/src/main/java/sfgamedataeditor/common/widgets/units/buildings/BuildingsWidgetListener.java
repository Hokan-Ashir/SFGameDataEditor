package sfgamedataeditor.common.widgets.units.buildings;

import sfgamedataeditor.common.cache.ImageIconsCache;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.buildings.army.requirements.BuildingsArmyRequirementsObject;
import sfgamedataeditor.database.buildings.army.requirements.BuildingsArmyRequirementsTableService;
import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsObject;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersModel;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersModelParameter;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersView;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class BuildingsWidgetListener extends AbstractWidgetListener<BuildingsWidget, OffsetableObject> implements ItemListener, ActionListener {

    public BuildingsWidgetListener(BuildingsWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        String selectedBuildingName = (String) getWidget().getBuildingComboBox().getSelectedItem();
        int buildingId = ViewTools.getKeyByPropertyValue(selectedBuildingName, I18NTypes.BUILDING_NAMES_MAPPING);
        return new int[]{buildingId};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int buildingId = value[0];
        final String buildingName = I18NService.INSTANCE.getMessage(I18NTypes.BUILDING_NAMES_MAPPING, String.valueOf(buildingId));
        Integer raceId = BuildingsTableService.INSTANCE.getRaceIdByBuildingName(buildingName);
        final String raceName = I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(raceId));
        final JComboBox<String> racesComboBox = getWidget().getRacesComboBox();
        racesComboBox.setSelectedItem(raceName);

        final JComboBox<String> unitNameComboBox = getWidget().getBuildingComboBox();
        unitNameComboBox.setSelectedItem(buildingName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedBuildingName = (String) getWidget().getBuildingComboBox().getSelectedItem();
        BuildingsObject buildingsObject = BuildingsTableService.INSTANCE.getBuildingObjectByBuildingName(selectedBuildingName);
        List<BuildingsRequirementsObject> requirementsObjects = BuildingsRequirementsTableService.INSTANCE.getBuildingRequirementsObjectsByBuildingName(selectedBuildingName);
        List<BuildingsArmyRequirementsObject> buildingArmyObjects = BuildingsArmyRequirementsTableService.INSTANCE.getBuildingArmyObjectByBuildingName(selectedBuildingName);
        Icon icon = getBuildingIcon(selectedBuildingName);
        BuildingsParametersModelParameter parameter = new BuildingsParametersModelParameter(buildingsObject, requirementsObjects, buildingArmyObjects, icon);
        BuildingsParametersModel model = new BuildingsParametersModel(parameter);
        ShowContentViewEvent event = new ShowContentViewEvent(BuildingsParametersView.class, model);
        EventProcessor.INSTANCE.process(event);
    }

    private Icon getBuildingIcon(String buildingName) {
        Integer buildingId = ViewTools.getKeyByPropertyValue(buildingName, I18NTypes.BUILDING_NAMES_MAPPING);
        String iconPath = "/images/buildings/" + buildingId + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        if (e.getSource().equals(getWidget().getRacesComboBox())) {
            updateUnitNamesComboBox();
        }

        if (getWidget().getBuildingComboBox().getSelectedItem() != null
                && getWidget().getRacesComboBox().getSelectedItem() != null) {
            setWidgetValueToDTOField();
        }
    }

    private void updateUnitNamesComboBox() {
        String selectedRaceName = (String) getWidget().getRacesComboBox().getSelectedItem();
        final Set<String> buildingsNames = BuildingsTableService.INSTANCE.getBuildingsNamesByRaceName(selectedRaceName);
        final JComboBox<String> comboBox = getWidget().getBuildingComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
            @Override
            protected void setValues() {
                comboBox.setSelectedItem(null);
                comboBox.removeAllItems();
                for (String buildingName : buildingsNames) {
                    comboBox.addItem(buildingName);
                }
            }
        });
    }
}
