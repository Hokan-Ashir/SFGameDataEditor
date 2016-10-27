package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.SpellName;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.tableservices.SpellNameTableService;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.fieldwrapping.FieldsWrapperCreator;
import sfgamedataeditor.fieldwrapping.MappedColumn;
import sfgamedataeditor.fieldwrapping.fields.IDataField;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.Processable;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class SpellParameterView extends AbstractView<MainView> implements Processable<SpellParameterViewMetaEvent> {

    private static final Logger LOGGER = Logger.getLogger(SkillParameterView.class);

    private final SpellParameterViewStub stub;
    private final Collection<IDataField> dataFields;
    private SpellParameterEventParameter parameter;
    // TODO get rid of this as private variable
    private LevelableView<SpellsView> view = (LevelableView<SpellsView>) ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, MainView.class));
    private SpellsView spellsView = (SpellsView) ViewRegister.INSTANCE.getView(new ClassTuple(SpellsView.class, MainView.class));
    private SpellParameterViewMetaEvent event = new SpellParameterViewMetaEvent();

    public SpellParameterView(MainView parentView) {
        super(parentView);
        stub = new SpellParameterViewStub();
        dataFields = FieldsWrapperCreator.createFieldWrappers(stub);
        view.getLevelComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                SpellParameterEventParameter eventParameter = new SpellParameterEventParameter(parameter.getSpellId(), view.getSelectedLevel());
                event.setEventParameter(ShowSpellParameterViewEvent.class, eventParameter);
                event.setEventParameter(SetModuleNameEvent.class, spellsView.getSelectedModuleValue());
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
        Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(selectedSpellId);
        setSpellAvaliableLevels(spellLevels, selectedLevel);

        int spellMinLevel = (int) ((TreeSet) spellLevels).first();
        int spellMaxLevel = (int) ((TreeSet) spellLevels).last();
        selectedLevel = adjustSelectedLevel(selectedLevel, spellMinLevel, spellMaxLevel);
        SpellParameters spellParameter = SpellParametersTableService.INSTANCE.getSpellParameter(selectedSpellId, selectedLevel);
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
        String selectedSpellName = spellsView.getSelectedModuleValue();
        SpellName spellName = SpellNameTableService.INSTANCE.getSpellName(selectedSpellName);
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
                JLabel label = ((JLabel) field.get(stub));
                if (parameterName.equals(ViewTools.convertToMultiline(I18N.INSTANCE.getMessage("spellParameterNotUsed")))) {
                    label.setVisible(false);
                    label.getLabelFor().setVisible(false);
                } else {
                    label.setVisible(true);
                    label.getLabelFor().setVisible(true);
                    label.setText(parameterName);
                }
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage());
            }
        }

        repaint();
    }

    private String getParameterName(SpellName spellName, String fieldName) {
        try {
            Field field = spellName.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (String) field.get(spellName);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<SpellParameterViewMetaEvent> getMetaEventClass() {
        return SpellParameterViewMetaEvent.class;
    }
}
