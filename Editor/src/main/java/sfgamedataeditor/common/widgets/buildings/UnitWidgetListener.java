package sfgamedataeditor.common.widgets.buildings;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.units.races.UnitMapping;
import sfgamedataeditor.views.main.modules.units.races.units.UnitModelCreator;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersModel;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersView;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.List;

public class UnitWidgetListener extends AbstractWidgetListener<UnitWidget, OffsetableObject> implements ItemListener, ActionListener {

    private final UnitModelCreator modelCreator = new UnitModelCreator();

    public UnitWidgetListener(UnitWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        ObjectTuple tuple = (ObjectTuple) getWidget().getUnitComboBox().getSelectedItem();
        return new int[]{tuple.getObjectId()};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int unitId = value[0];
        final ObjectTuple raceTuple = UnitMapping.INSTANCE.getRaceTuple(unitId);
        final JComboBox<ObjectTuple> racesComboBox = getWidget().getRacesComboBox();
        racesComboBox.setSelectedItem(raceTuple);
        updateUnitNamesComboBox();

        final JComboBox<ObjectTuple> unitNameComboBox = getWidget().getUnitComboBox();
        ObjectTuple unitTuple = UnitMapping.INSTANCE.getUnitTuple(unitId);
        unitNameComboBox.setSelectedItem(unitTuple);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ObjectTuple tuple = (ObjectTuple) getWidget().getUnitComboBox().getSelectedItem();
        Icon icon = getUnitIcon(tuple.getObjectId());
        UnitsParametersModel model = modelCreator.createModel(tuple.getObjectId(), icon);
        ShowContentViewEvent event = new ShowContentViewEvent(UnitsParametersView.class, model);
        EventProcessor.INSTANCE.process(event);
    }

    private Icon getUnitIcon(Integer creatureId) {
        String iconPath = "/images/units/" + creatureId + ".png";
        return ImageIconsCache.INSTANCE.getImageIcon(iconPath);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        if (getWidget().getUnitComboBox().getSelectedItem() != null
                && getWidget().getRacesComboBox().getSelectedItem() != null) {
            setWidgetValueToDTOField();
        }
    }

    private void updateUnitNamesComboBox() {
        ObjectTuple tuple = (ObjectTuple) getWidget().getRacesComboBox().getSelectedItem();
        final List<ObjectTuple> creatureNames = UnitMapping.INSTANCE.getUnitNames(tuple);
        final JComboBox<ObjectTuple> comboBox = getWidget().getUnitComboBox();
        ViewTools.replaceComboBoxContentSilently(comboBox, creatureNames);
    }
}
