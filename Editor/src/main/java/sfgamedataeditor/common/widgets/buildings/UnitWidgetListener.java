package sfgamedataeditor.common.widgets.buildings;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.common.SubViewPanelTuple;
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
        String selectedCreatureName = (String) getWidget().getUnitComboBox().getSelectedItem();
        Integer creatureId = CreatureCommonParametersTableService.INSTANCE.getCreatureIdByName(selectedCreatureName);
        return new int[]{creatureId};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int unitId = value[0];
        final String raceName = UnitMapping.INSTANCE.getRaceName(unitId);
        final JComboBox<String> racesComboBox = getWidget().getRacesComboBox();
        racesComboBox.setSelectedItem(raceName);

        final JComboBox<SubViewPanelTuple> unitNameComboBox = getWidget().getUnitComboBox();
        String unitName = UnitMapping.INSTANCE.getUnitName(unitId);
        unitNameComboBox.setSelectedItem(unitName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedCreatureName = (String) getWidget().getUnitComboBox().getSelectedItem();
        Integer creatureId = CreatureCommonParametersTableService.INSTANCE.getCreatureIdByName(selectedCreatureName);
        Icon icon = getUnitIcon(creatureId);
        UnitsParametersModel model = modelCreator.createModel(creatureId, icon);
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

        if (e.getSource().equals(getWidget().getRacesComboBox())) {
            updateUnitNamesComboBox();
        }

        if (getWidget().getUnitComboBox().getSelectedItem() != null
                && getWidget().getRacesComboBox().getSelectedItem() != null) {
            setWidgetValueToDTOField();
        }
    }

    private void updateUnitNamesComboBox() {
        String selectedRaceName = (String) getWidget().getRacesComboBox().getSelectedItem();
        final List<SubViewPanelTuple> creatureNames = UnitMapping.INSTANCE.getUnitNames(selectedRaceName);
        final JComboBox<SubViewPanelTuple> comboBox = getWidget().getUnitComboBox();
        ViewTools.replaceComboBoxContentSilently(comboBox, creatureNames);
    }
}
