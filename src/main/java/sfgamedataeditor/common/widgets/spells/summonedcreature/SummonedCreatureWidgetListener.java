package sfgamedataeditor.common.widgets.spells.summonedcreature;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.database.creatures.common.CreaturesCommonParameterObject;
import sfgamedataeditor.database.creatures.corpseloot.CreatureCorpseLootObject;
import sfgamedataeditor.database.creatures.corpseloot.CreatureCorpseLootTableService;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentObject;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentTableService;
import sfgamedataeditor.database.creatures.parameters.CreatureParameterObject;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.database.creatures.spells.CreatureSpellObject;
import sfgamedataeditor.database.creatures.spells.CreatureSpellTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModel;
import sfgamedataeditor.views.main.modules.creatures.races.creatures.parameters.CreaturesParametersModelParameter;
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
import java.util.List;
import java.util.Set;

public class SummonedCreatureWidgetListener extends AbstractWidgetListener<SummonedCreatureWidget, OffsetableObject> implements ItemListener, ActionListener {

    public SummonedCreatureWidgetListener(SummonedCreatureWidget component, Field... mappedFields) {
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
        int creatureId = ViewTools.getKeyByPropertyValue(selectedCreatureName, I18NTypes.CREATURES);
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
        CreatureParameterObject creatureParameterObject = CreatureParametersTableService.INSTANCE.getCreatureParameterObjectByCreatureName(selectedCreatureName);
        Integer creatureId = ViewTools.getKeyByPropertyValue(selectedCreatureName, I18NTypes.CREATURES);
        CreaturesCommonParameterObject commonParameterObject = CreatureCommonParametersTableService.INSTANCE.getCreatureParametersByCreatureId(creatureId);
        List<CreatureEquipmentObject> creatureEquipment = CreatureEquipmentTableService.INSTANCE.getCreatureEquipmentByCreatureId(creatureId);
        List<CreatureSpellObject> creatureSpells = CreatureSpellTableService.INSTANCE.getCreatureSpellsByCreatureId(creatureId);
        List<CreatureCorpseLootObject> corpseLootObjects = CreatureCorpseLootTableService.INSTANCE.getCreatureCorpseLootByCreatureId(creatureId);
        CreaturesParametersModelParameter parameter = new CreaturesParametersModelParameter(creatureParameterObject, commonParameterObject,
                creatureEquipment, creatureSpells, corpseLootObjects);
        CreaturesParametersModel model = new CreaturesParametersModel(parameter);
        ShowContentViewEvent event = new ShowContentViewEvent(CreaturesParametersView.class, model);
        EventProcessor.INSTANCE.process(event);
    }
}
