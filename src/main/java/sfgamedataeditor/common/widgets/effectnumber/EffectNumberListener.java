package sfgamedataeditor.common.widgets.effectnumber;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.objects.OffsetableObject;
import sfgamedataeditor.database.objects.SpellName;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.tableservices.SpellNameTableService;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

public class EffectNumberListener extends AbstractWidgetListener<EffectNumberWidget, OffsetableObject> implements ItemListener {

    public EffectNumberListener(EffectNumberWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        String spellName = (String) getWidget().getSpellNameComboBox().getSelectedItem();
        SpellName spellNameObject = SpellNameTableService.INSTANCE.getSpellName(spellName);

        String spellLevel = (String) getWidget().getSpellLevelComboBox().getSelectedItem();
        SpellParameters selectedSpell = SpellParametersTableService.INSTANCE.getSpellParameterBySpellIdAndLevel(spellNameObject.spellType, Integer.parseInt(spellLevel));

        return new int[] {selectedSpell.spellNumber};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int spellNumber = value[0];
        SpellParameters spellParameters = SpellParametersTableService.INSTANCE.getSpellParametersBySpellNumber(spellNumber);
        String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParameters.spellNameId);
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

        final Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(spellParameters.spellNameId);
        final JComboBox<String> spellLevelComboBox = getWidget().getSpellLevelComboBox();
        spellLevelComboBox.removeAll();
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(spellLevelComboBox) {
            @Override
            protected void setValues() {
                for (Integer spellLevel : spellLevels) {
                    spellLevelComboBox.addItem(String.valueOf(spellLevel));
                }
            }
        });

        Integer requirementLevel1 = spellParameters.requirementLevel1;
        Integer requirementLevel2 = spellParameters.requirementLevel2;
        Integer requirementLevel3 = spellParameters.requirementLevel3;

        int spellMinLevel = (int) ((TreeSet) spellLevels).first();
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

        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(spellLevelComboBox) {
            @Override
            protected void setValues() {
                for (Integer spellLevel : spellLevels) {
                    spellLevelComboBox.setSelectedItem(selectedItem);
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
}
