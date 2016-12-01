package sfgamedataeditor.common.widgets.effectnumber;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.spellname.SpellNameObject;
import sfgamedataeditor.database.spellname.SpellNameTableService;
import sfgamedataeditor.database.spellparameters.SpellParametersObject;
import sfgamedataeditor.database.spellparameters.SpellParametersTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModelParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

public class EffectNumberListener extends AbstractWidgetListener<EffectNumberWidget, OffsetableObject> implements ItemListener, ActionListener {

    public EffectNumberListener(EffectNumberWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        SpellParametersObject selectedSpell = getSelectedSpellParameterObject();
        return new int[] {selectedSpell.spellNumber};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int spellNumber = value[0];
        SpellParametersObject spellParametersObject = SpellParametersTableService.INSTANCE.getSpellParametersBySpellNumber(spellNumber);
        setSelectedSpellName(spellParametersObject);
        setSelectedSpellLevel(spellParametersObject);
    }

    private void setSelectedSpellName(SpellParametersObject spellParametersObject) {
        String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParametersObject.spellNameId);
        final JComboBox<String> spellNameComboBox = getWidget().getSpellNameComboBox();
        ComboBoxModel<String> comboBoxModel = spellNameComboBox.getModel();
        int size = comboBoxModel.getSize();
        for (int i = 0; i < size; ++i) {
            String element = comboBoxModel.getElementAt(i);
            if (element.equals(spellName)) {
                final int finalI = i;
                ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(spellNameComboBox) {
                    @Override
                    protected void setValues() {
                        spellNameComboBox.setSelectedItem(spellNameComboBox.getItemAt(finalI));
                    }
                });
                break;
            }
        }
    }

    private void setSelectedSpellLevel(SpellParametersObject spellParametersObject) {
        final Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(spellParametersObject.spellNameId);
        final JComboBox<String> spellLevelComboBox = getWidget().getSpellLevelComboBox();
        setPossibleSpellLevels(spellLevels, spellLevelComboBox);

        final Object selectedItem = getSelectedSpellLevel(spellParametersObject, (TreeSet) spellLevels, spellLevelComboBox);

        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(spellLevelComboBox) {
            @Override
            protected void setValues() {
                spellLevelComboBox.setSelectedItem(selectedItem);
            }
        });
    }

    private Object getSelectedSpellLevel(SpellParametersObject spellParametersObject, TreeSet spellLevels, JComboBox<String> spellLevelComboBox) {
        Integer requirementLevel1 = spellParametersObject.requirementLevel1;
        Integer requirementLevel2 = spellParametersObject.requirementLevel2;
        Integer requirementLevel3 = spellParametersObject.requirementLevel3;

        int spellMinLevel = (int) spellLevels.first();
        final Object selectedItem;
        if (requirementLevel1 != 0 || requirementLevel2 != 0 || requirementLevel3 != 0) {
            if (requirementLevel1 != 0) {
                selectedItem = spellLevelComboBox.getItemAt(requirementLevel1 - spellMinLevel);
            } else if (requirementLevel2 != 0) {
                selectedItem = spellLevelComboBox.getItemAt(requirementLevel2 - spellMinLevel);
            } else {
                selectedItem = spellLevelComboBox.getItemAt(requirementLevel3 - spellMinLevel);
            }
        } else {
            selectedItem = spellLevelComboBox.getItemAt(0);
        }
        return selectedItem;
    }

    private void setPossibleSpellLevels(final Set<Integer> spellLevels, final JComboBox<String> spellLevelComboBox) {
        spellLevelComboBox.removeAll();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(spellLevelComboBox) {
            @Override
            protected void setValues() {
                for (Integer spellLevel : spellLevels) {
                    spellLevelComboBox.addItem(String.valueOf(spellLevel));
                }
            }
        });
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        setWidgetValueToDTOField();
    }

    private Integer getSelectedSpellLevel(SpellParametersObject spellParametersObject) {
        Integer requirementLevel1 = spellParametersObject.requirementLevel1;
        Integer requirementLevel2 = spellParametersObject.requirementLevel2;
        Integer requirementLevel3 = spellParametersObject.requirementLevel3;

        int spellLevel;
        if (requirementLevel1 != 0 || requirementLevel2 != 0 || requirementLevel3 != 0) {
            if (requirementLevel1 != 0) {
                spellLevel = requirementLevel1;
            } else if (requirementLevel2 != 0) {
                spellLevel = requirementLevel2;
            } else {
                spellLevel = requirementLevel3;
            }
        } else {
            spellLevel = 0;
        }


        return spellLevel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SpellParametersObject selectedSpellParameterObject = getSelectedSpellParameterObject();
        Integer selectedSpellLevel = getSelectedSpellLevel(selectedSpellParameterObject);
        Integer selectedSpellNameId = selectedSpellParameterObject.spellNameId;
        SpellParameterModelParameter parameter = new SpellParameterModelParameter(selectedSpellNameId, selectedSpellLevel);
        SpellParameterModel model = new SpellParameterModel(parameter);
        ShowContentViewEvent event = new ShowContentViewEvent(SpellParameterView.class, model);
        EventProcessor.INSTANCE.process(event);
    }

    private SpellParametersObject getSelectedSpellParameterObject() {
        String spellName = (String) getWidget().getSpellNameComboBox().getSelectedItem();
        SpellNameObject spellNameObject = SpellNameTableService.INSTANCE.getSpellName(spellName);

        String spellLevel = (String) getWidget().getSpellLevelComboBox().getSelectedItem();
        return SpellParametersTableService.INSTANCE.getSpellParameterBySpellIdAndLevel(spellNameObject.spellType, Integer.parseInt(spellLevel));
    }
}
