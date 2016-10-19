package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.database.TableCreationUtils;
import sfgamedataeditor.database.objects.OffsetableObject;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.dataextraction.SpellMap;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.fieldwrapping.FieldsWrapperCreator;
import sfgamedataeditor.fieldwrapping.fields.IDataField;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

public class SpellParameterView extends AbstractView<SpellsView> {

    private static final int LABEL_LINE_MAX_LENGTH = 12;
    private final SpellParameterViewStub stub;
    private final Collection<IDataField> dataFields;
    private SpellParameterEventParameter parameter;
    // TODO get rid of this as private variable
    private LevelableView<SpellsView> view = (LevelableView<SpellsView>) ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, SpellsView.class));

    public SpellParameterView(SpellsView parentView) {
        super(parentView);
        stub = new SpellParameterViewStub();
        dataFields = FieldsWrapperCreator.createFieldWrappers(stub);
        view.getLevelComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                // TODO get rid of multiple new event instances
                SpellParameterViewMetaEvent event = new SpellParameterViewMetaEvent();
                SpellParameterEventParameter eventParameter = new SpellParameterEventParameter(parameter.getSpellId(), view.getSelectedLevel());
                event.setEventParameter(ShowSpellParameterViewEvent.class, eventParameter);
                EventHandlerRegister.INSTANCE.fireEvent(event);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateData(Object data) {
        if (data != null) {
            // TODO get rid of class casting
            parameter = (SpellParameterEventParameter) data;
        }

        int selectedSpellId = parameter.getSpellId();
        int selectedLevel = parameter.getSpellLevel();
        Set<Integer> spellLevels = TableCreationUtils.getSpellLevels(selectedSpellId);
        int spellMinLevel = (int) ((TreeSet)spellLevels).first();
        int spellMaxLevel = (int) ((TreeSet)spellLevels).last();
        setSpellAvaliableLevels(spellLevels, selectedLevel);
        selectedLevel = adjustSelectedLevel(selectedLevel, spellMinLevel, spellMaxLevel);
        List<SpellParameters> spellParameters = TableCreationUtils.getSpellParameters(selectedSpellId, selectedLevel);
        SpellParameters spellParameter = spellParameters.get(0);
        for (IDataField dataField : dataFields) {
            dataField.mapValues(spellParameter);
        }

        setSpellParameterLabelNames();
    }

    // in case user selected spell with level-range [1; 12] with level 5
    // then selected spell with level range [13; 20]
    // we have to select level 13 to appropriately load correct data
    // and vice versa, if user selected spell with level range [13; 20]
    // and then selected spell with level range [1; 12]
    private int adjustSelectedLevel(int selectedLevel, int spellMinLevel, int spellMaxLevel) {
        if (selectedLevel <= spellMaxLevel && selectedLevel >= spellMinLevel) {
            return selectedLevel;
        } else {
            return spellMinLevel;
        }
    }

    private void setSpellAvaliableLevels(Set<Integer> spellLevels, int selectedLevel) {
        JComboBox<String> comboBox = view.getLevelComboBox();
        ItemListener[] listeners = comboBox.getItemListeners();
        for (ItemListener listener : listeners) {
            comboBox.removeItemListener(listener);
        }

        comboBox.removeAllItems();
        for (Integer spellLevel : spellLevels) {
            comboBox.addItem(String.valueOf(spellLevel));
        }

        // TODO split this methods
        setLevelComboBoxItem(selectedLevel);

        for (ItemListener listener : listeners) {
            comboBox.addItemListener(listener);
        }
    }

    private void setLevelComboBoxItem(int spellLevel) {
        JComboBox<String> comboBox = view.getLevelComboBox();
        comboBox.setSelectedItem(String.valueOf(spellLevel));
    }

    private void setSpellParameterLabelNames() {
        List<JLabel> parameterLabels = stub.getParameterLabels();
        String selectedSpellName = (String) getParentView().getSelectedModuleValue();
        for (Map.Entry<Integer, Pair<String, List<String>>> integerPairEntry : SpellMap.INSTANCE.getSpellMap().entrySet()) {
            String spellName = integerPairEntry.getValue().getKey();
            if (selectedSpellName.equals(spellName)) {
                List<String> spellParameterNames = integerPairEntry.getValue().getValue();
                for (int i = 0; i < parameterLabels.size(); i++) {
                    if (i < spellParameterNames.size()) {
                        parameterLabels.get(i).setText(convertToMultiline(spellParameterNames.get(i)));
                    } else {
                        parameterLabels.get(i).setText(I18N.INSTANCE.getMessage("spellParameterNotUsed"));
                    }
                }
                break;
            }
        }
    }

    private String convertToMultiline(String value) {
        String[] subStrings = value.split(" ");
        String result = "<html>";
        int lastNewLineInjectionPosition = 0;
        for (int i = 0; i < subStrings.length; ++i) {
            result = result + subStrings[i] + " ";
            if (result.length() - lastNewLineInjectionPosition > LABEL_LINE_MAX_LENGTH
                    && i != subStrings.length - 1) {
                result = result + "<br>";
                lastNewLineInjectionPosition = result.length();
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return stub.getMainPanel();
    }
}
