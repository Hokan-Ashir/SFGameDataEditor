package sfgamedataeditor.common.widgets.spells.creature;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.CreaturesModelCreator;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersView;
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
import java.util.Set;

public class CreatureWidgetListener extends AbstractWidgetListener<CreatureWidget, OffsetableObject> implements ItemListener, ActionListener {

    private CreaturesModelCreator modelCreator = new CreaturesModelCreator();

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
        String selectedRaceName = (String) getWidget().getRacesComboBox().getSelectedItem();
        final Set<String> creatureNames = CreatureCommonParametersTableService.INSTANCE.getCreatureNamesByRaceName(selectedRaceName);
        final JComboBox<String> comboBox = getWidget().getCreatureNameComboBox();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(comboBox) {
            @Override
            protected void setValues() {
                comboBox.setSelectedItem(null);
                comboBox.removeAllItems();
                for (String creatureName : creatureNames) {
                    comboBox.addItem(creatureName);
                }
            }
        });

    }

    @Override
    protected int[] getFieldValues() {
        String selectedCreatureName = (String) getWidget().getCreatureNameComboBox().getSelectedItem();
        Integer creatureId = CreatureCommonParametersTableService.INSTANCE.getCreatureIdByName(selectedCreatureName);
        return new int[]{creatureId};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int creatureId = value[0];
        final String creatureName = I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, String.valueOf(creatureId));
        Integer raceId = CreatureParametersTableService.INSTANCE.getRaceIdByCreatureName(creatureName);
        final String raceName = I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(raceId));
        final JComboBox<String> racesComboBox = getWidget().getRacesComboBox();
        racesComboBox.setSelectedItem(raceName);

        final JComboBox<String> creatureNameComboBox = getWidget().getCreatureNameComboBox();
        creatureNameComboBox.setSelectedItem(creatureName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedCreatureName = (String) getWidget().getCreatureNameComboBox().getSelectedItem();
        Integer creatureId = CreatureCommonParametersTableService.INSTANCE.getCreatureIdByName(selectedCreatureName);
        CreaturesParametersModel model = modelCreator.createModel(creatureId, null);
        ShowContentViewEvent event = new ShowContentViewEvent(CreaturesParametersView.class, model);
        EventProcessor.INSTANCE.process(event);
    }
}
