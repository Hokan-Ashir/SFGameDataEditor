package sfgamedataeditor.common.widgets.effectnumber;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.objects.OffsetableObject;
import sfgamedataeditor.database.objects.SpellParameters;
import sfgamedataeditor.database.tableservices.SpellNameTableService;
import sfgamedataeditor.database.tableservices.SpellParametersTableService;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.Set;

public class EffectNumberListener extends AbstractWidgetListener<EffectNumberWidget, OffsetableObject> implements ItemListener {

    public EffectNumberListener(EffectNumberWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        // TODO seems awkward, need to be fixed
        return null;
    }

    @Override
    protected void setFieldValues(int[] value) {
        int spellNumber = value[0];
        SpellParameters spellParameters = SpellParametersTableService.INSTANCE.getSpellParametersBySpellNumber(spellNumber);
        String spellName = SpellNameTableService.INSTANCE.getSpellName(spellParameters.spellNameId);
        JComboBox<String> spellNameComboBox = getWidget().getSpellNameComboBox();
        ComboBoxModel<String> comboBoxModel = spellNameComboBox.getModel();
        int size = comboBoxModel.getSize();
        for (int i = 0; i < size; ++i) {
            String element = comboBoxModel.getElementAt(i);
            if (element.equals(spellName)) {
                spellNameComboBox.setSelectedItem(spellNameComboBox.getItemAt(i));
                break;
            }
        }

        Set<Integer> spellLevels = SpellParametersTableService.INSTANCE.getSpellLevels(spellParameters.spellNameId);
        JComboBox<String> spellLevelComboBox = getWidget().getSpellLevelComboBox();
        spellLevelComboBox.removeAll();
        for (Integer spellLevel : spellLevels) {
            spellLevelComboBox.addItem(String.valueOf(spellLevel));
        }

        Integer requirementLevel1 = spellParameters.requirementLevel1;
        Integer requirementLevel2 = spellParameters.requirementLevel2;
        Integer requirementLevel3 = spellParameters.requirementLevel3;

        // TODO set correct value
        spellLevelComboBox.getItemAt(0);
//        if (requirementLevel1 != 0 || requirementLevel2 != 0 || requirementLevel3 != 0) {
//        } else {
//
//        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
