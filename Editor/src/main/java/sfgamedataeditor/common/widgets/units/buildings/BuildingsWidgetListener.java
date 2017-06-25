package sfgamedataeditor.common.widgets.units.buildings;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingModelCreator;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersModel;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersView;
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

public class BuildingsWidgetListener extends AbstractWidgetListener<BuildingsWidget, OffsetableObject> implements ItemListener, ActionListener {

    private final BuildingModelCreator modelCreator = new BuildingModelCreator();

    public BuildingsWidgetListener(BuildingsWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        SubViewPanelTuple tuple = (SubViewPanelTuple) getWidget().getBuildingComboBox().getSelectedItem();
        return new int[]{tuple.getObjectId()};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int buildingId = value[0];
        // TODO
        Integer raceId = BuildingsTableService.INSTANCE.getRaceIdByBuildingName(buildingId);
        final String raceName = I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(raceId));
        final JComboBox<SubViewPanelTuple> racesComboBox = getWidget().getRacesComboBox();
        racesComboBox.setSelectedItem(raceName);

        final JComboBox<SubViewPanelTuple> unitNameComboBox = getWidget().getBuildingComboBox();
        BuildingsObject object = BuildingsTableService.INSTANCE.getBuildingObjectByBuildingId(buildingId);
        String buildingName = TextTableService.INSTANCE.getObjectName(object.nameId);
        unitNameComboBox.setSelectedItem(buildingName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SubViewPanelTuple tuple = (SubViewPanelTuple) getWidget().getBuildingComboBox().getSelectedItem();
        Integer buildingId = tuple.getObjectId();
        Icon icon = getBuildingIcon(buildingId);
        BuildingsParametersModel model = modelCreator.createModel(buildingId, icon);
        ShowContentViewEvent event = new ShowContentViewEvent(BuildingsParametersView.class, model);
        EventProcessor.INSTANCE.process(event);
    }

    private Icon getBuildingIcon(Integer buildingId) {
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
        SubViewPanelTuple item = (SubViewPanelTuple) getWidget().getRacesComboBox().getSelectedItem();
        final List<SubViewPanelTuple> buildingsNames = BuildingsTableService.INSTANCE.getBuildingsNamesByRaceId(item.getObjectId());
        final JComboBox<SubViewPanelTuple> comboBox = getWidget().getBuildingComboBox();
        ViewTools.replaceComboBoxContentSilently(comboBox, buildingsNames);
    }
}
