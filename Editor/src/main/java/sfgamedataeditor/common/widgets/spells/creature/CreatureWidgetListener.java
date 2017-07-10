package sfgamedataeditor.common.widgets.spells.creature;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModelCreator;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;
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

public class CreatureWidgetListener extends AbstractWidgetListener<CreatureWidget, OffsetableObject> implements ItemListener, ActionListener {

    private final CreaturesModelCreator modelCreator = new CreaturesModelCreator();

    public CreatureWidgetListener(CreatureWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        if (e.getSource().equals(getWidget().getRacesComboBox())) {
            updateCreatureNamesComboBox();
        }

        if (getWidget().getCreatureNameComboBox().getSelectedItem() != null
                && getWidget().getRacesComboBox().getSelectedItem() != null) {
            setWidgetValueToDTOField();
        }
    }

    private void updateCreatureNamesComboBox() {
        ObjectTuple tuple = (ObjectTuple) getWidget().getRacesComboBox().getSelectedItem();
        final List<ObjectTuple> creatureNames = CreatureCommonParametersTableService.INSTANCE.getCreatureNamesByRaceId(tuple.getObjectId());
        final JComboBox<ObjectTuple> comboBox = getWidget().getCreatureNameComboBox();
        ViewTools.replaceComboBoxContentSilently(comboBox, creatureNames);
    }

    @Override
    protected int[] getFieldValues() {
        ObjectTuple tuple = (ObjectTuple) getWidget().getCreatureNameComboBox().getSelectedItem();
        return new int[]{tuple.getObjectId()};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int creatureId = value[0];

        // TODO
        Integer raceId = CreatureParametersTableService.INSTANCE.getRaceIdByCreatureName(creatureId);
        final String raceName = I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(raceId));
        final JComboBox<ObjectTuple> racesComboBox = getWidget().getRacesComboBox();
        racesComboBox.setSelectedItem(raceName);

        final JComboBox<ObjectTuple> creatureNameComboBox = getWidget().getCreatureNameComboBox();
        List<ObjectTuple> tuples = CreatureCommonParametersTableService.INSTANCE.getCreatureTuples(new Integer[]{creatureId});
        creatureNameComboBox.setSelectedItem(tuples.get(0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ObjectTuple tuple = (ObjectTuple) getWidget().getCreatureNameComboBox().getSelectedItem();
        // TODO extract icon from DB object
        CreaturesParametersModel model = modelCreator.createModel(tuple.getObjectId());
        ShowContentViewEvent event = new ShowContentViewEvent(CreaturesParametersView.class, model);
        EventProcessor.INSTANCE.process(event);
    }
}
