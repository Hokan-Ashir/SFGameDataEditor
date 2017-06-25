package sfgamedataeditor.common.widgets.spells.creature;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.common.SubViewPanelTuple;
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
        SubViewPanelTuple tuple = (SubViewPanelTuple) getWidget().getRacesComboBox().getSelectedItem();
        final List<SubViewPanelTuple> creatureNames = CreatureCommonParametersTableService.INSTANCE.getCreatureNamesByRaceId(tuple.getObjectId());
        final JComboBox<SubViewPanelTuple> comboBox = getWidget().getCreatureNameComboBox();
        ViewTools.replaceComboBoxContentSilently(comboBox, creatureNames);
    }

    @Override
    protected int[] getFieldValues() {
        SubViewPanelTuple tuple = (SubViewPanelTuple) getWidget().getCreatureNameComboBox().getSelectedItem();
        return new int[]{tuple.getObjectId()};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int creatureId = value[0];

        // TODO
        Integer raceId = CreatureParametersTableService.INSTANCE.getRaceIdByCreatureName(creatureId);
        final String raceName = I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(raceId));
        final JComboBox<SubViewPanelTuple> racesComboBox = getWidget().getRacesComboBox();
        racesComboBox.setSelectedItem(raceName);

        final JComboBox<SubViewPanelTuple> creatureNameComboBox = getWidget().getCreatureNameComboBox();
        Integer[] nameIds = CreatureCommonParametersTableService.INSTANCE.getNameIds(new Integer[]{creatureId});
        List<String> objectNames = TextTableService.INSTANCE.getObjectNames(nameIds);
        creatureNameComboBox.setSelectedItem(objectNames.get(0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedCreatureName = (String) getWidget().getCreatureNameComboBox().getSelectedItem();
        Integer creatureId = CreatureCommonParametersTableService.INSTANCE.getCreatureIdByName(selectedCreatureName);
        // TODO extract icon from DB object
        CreaturesParametersModel model = modelCreator.createModel(creatureId, null);
        ShowContentViewEvent event = new ShowContentViewEvent(CreaturesParametersView.class, model);
        EventProcessor.INSTANCE.process(event);
    }
}
