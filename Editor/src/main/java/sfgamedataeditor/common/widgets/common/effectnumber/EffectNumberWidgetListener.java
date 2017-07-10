package sfgamedataeditor.common.widgets.common.effectnumber;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.spells.names.SpellNameObject;
import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.database.spells.parameters.SpellParametersObject;
import sfgamedataeditor.database.spells.parameters.SpellParametersTableService;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.views.common.ObjectTuple;
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

public class EffectNumberWidgetListener extends AbstractWidgetListener<EffectNumberWidget, OffsetableObject> implements ItemListener, ActionListener {

    public EffectNumberWidgetListener(EffectNumberWidget component, Field... mappedFields) {
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
        final JComboBox<ObjectTuple> spellNameComboBox = getWidget().getSpellNameComboBox();
        ComboBoxModel<ObjectTuple> comboBoxModel = spellNameComboBox.getModel();
        int size = comboBoxModel.getSize();
        for (int i = 0; i < size; ++i) {
            ObjectTuple element = comboBoxModel.getElementAt(i);
            if (element.getName().equals(spellName)) {
                final int finalI = i;
                ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<ObjectTuple>(spellNameComboBox) {
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

        final Object selectedItem = getSelectedSpellLevel(spellParametersObject);
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(spellLevelComboBox) {
            @Override
            protected void setValues() {
                spellLevelComboBox.setSelectedItem(selectedItem);
            }
        });
    }

    private String getSelectedSpellLevel(SpellParametersObject spellParametersObject) {
        Integer requirementLevel1 = spellParametersObject.requirementLevel1;
        Integer requirementLevel2 = spellParametersObject.requirementLevel2;
        Integer requirementLevel3 = spellParametersObject.requirementLevel3;

        String selectedSpellLevel = null;
        if (requirementLevel1 != 0 || requirementLevel2 != 0 || requirementLevel3 != 0) {
            if (requirementLevel1 != 0) {
                selectedSpellLevel = String.valueOf(requirementLevel1);
            } else if (requirementLevel2 != 0) {
                selectedSpellLevel = String.valueOf(requirementLevel2);
            } else {
                selectedSpellLevel = String.valueOf(requirementLevel3);
            }
        }

        return selectedSpellLevel;
    }

    private void setPossibleSpellLevels(final Set<Integer> spellLevels, final JComboBox<String> spellLevelComboBox) {
        spellLevelComboBox.removeAllItems();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        SpellParametersObject selectedSpellParameterObject = getSelectedSpellParameterObject();
        String selectedSpellLevel = getSelectedSpellLevel(selectedSpellParameterObject);
        Integer selectedSpellLevelInteger;
        if (selectedSpellLevel == null) {
            selectedSpellLevelInteger = 0;
        } else {
            selectedSpellLevelInteger = Integer.valueOf(selectedSpellLevel);
        }
        Integer selectedSpellNameId = selectedSpellParameterObject.spellNameId;

        ObjectTuple tuple = (ObjectTuple) getWidget().getSpellNameComboBox().getSelectedItem();
        Icon icon = ImageIconsCache.INSTANCE.getImageIcon("/images/spells_and_scrolls/", tuple.getObjectId());
        SpellParameterModelParameter parameter = new SpellParameterModelParameter(selectedSpellNameId, selectedSpellLevelInteger, icon);
        SpellParameterModel model = new SpellParameterModel(parameter);
        ShowContentViewEvent event = new ShowContentViewEvent(SpellParameterView.class, model);
        EventProcessor.INSTANCE.process(event);
    }

    private SpellParametersObject getSelectedSpellParameterObject() {
        ObjectTuple tuple = (ObjectTuple) getWidget().getSpellNameComboBox().getSelectedItem();
        SpellNameObject spellNameObject = SpellNameTableService.INSTANCE.getSpellName(tuple.getName());

        String spellLevel = (String) getWidget().getSpellLevelComboBox().getSelectedItem();
        return SpellParametersTableService.INSTANCE.getSpellParameterBySpellIdAndLevel(spellNameObject.spellType, Integer.parseInt(spellLevel));
    }
}
