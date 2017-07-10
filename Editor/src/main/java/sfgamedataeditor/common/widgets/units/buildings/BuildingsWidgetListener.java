package sfgamedataeditor.common.widgets.units.buildings;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.common.ObjectTuple;
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
        ObjectTuple tuple = (ObjectTuple) getWidget().getBuildingComboBox().getSelectedItem();
        return new int[]{tuple.getObjectId()};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int buildingId = value[0];
        Integer raceId = BuildingsTableService.INSTANCE.getRaceIdByBuildingName(buildingId);
        final String raceName = I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(raceId));
        final JComboBox<ObjectTuple> racesComboBox = getWidget().getRacesComboBox();
        racesComboBox.setSelectedItem(new ObjectTuple(raceName, raceId));
        updateUnitNamesComboBox();

        final JComboBox<ObjectTuple> unitNameComboBox = getWidget().getBuildingComboBox();
        BuildingsObject object = BuildingsTableService.INSTANCE.getBuildingObjectByBuildingId(buildingId);
        ObjectTuple objectTuple = TextTableService.INSTANCE.getObjectTuple(object.nameId, object.buildingId);
        unitNameComboBox.setSelectedItem(objectTuple);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ObjectTuple tuple = (ObjectTuple) getWidget().getBuildingComboBox().getSelectedItem();
        Integer buildingId = tuple.getObjectId();
        Icon icon = ImageIconsCache.INSTANCE.getImageIcon("/images/buildings/", buildingId);
        BuildingsParametersModel model = modelCreator.createModel(buildingId);
        ShowContentViewEvent event = new ShowContentViewEvent(BuildingsParametersView.class, model);
        EventProcessor.INSTANCE.process(event);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        if (getWidget().getBuildingComboBox().getSelectedItem() != null
                && getWidget().getRacesComboBox().getSelectedItem() != null) {
            setWidgetValueToDTOField();
        }
    }

    private void updateUnitNamesComboBox() {
        ObjectTuple item = (ObjectTuple) getWidget().getRacesComboBox().getSelectedItem();
        final List<ObjectTuple> buildingsNames = BuildingsTableService.INSTANCE.getBuildingsNamesByRaceId(item.getObjectId());
        final JComboBox<ObjectTuple> comboBox = getWidget().getBuildingComboBox();
        ViewTools.replaceComboBoxContentSilently(comboBox, buildingsNames);
    }
}
