package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.TableCreationUtils;
import sfgamedataeditor.database.objects.SpellName;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.fieldwrapping.FieldsWrapperCreator;
import sfgamedataeditor.fieldwrapping.MappedColumn;
import sfgamedataeditor.fieldwrapping.fields.IDataField;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.*;

public class SpellParameterView extends AbstractView<SpellsView> {

    private static final Logger LOGGER = Logger.getLogger(SkillParameterView.class);

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
        String selectedSpellName = (String) getParentView().getSelectedModuleValue();
        SpellName spellName = TableCreationUtils.getSpellName(selectedSpellName);
        for (Field field : stub.getClass().getDeclaredFields()) {
            MappedColumn annotation = field.getAnnotation(MappedColumn.class);
            if (annotation == null) {
                continue;
            }

            if (!annotation.daoClass().equals(SpellName.class)) {
                continue;
            }

            String mappedFieldName = annotation.name();
            String parameterName = getParameterName(spellName, mappedFieldName);
            try {
                field.setAccessible(true);
                JLabel label = ((JLabel)field.get(stub));
                label.setText(parameterName);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    private String getParameterName(SpellName spellName, String fieldName) {
        for (Field field : spellName.getClass().getDeclaredFields()) {
            if (field.getName().equals(fieldName)) {
                try {
                    field.setAccessible(true);
                    return (String) field.get(spellName);
                } catch (IllegalAccessException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return stub.getMainPanel();
    }
}
