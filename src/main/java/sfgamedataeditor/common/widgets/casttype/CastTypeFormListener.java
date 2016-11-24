package sfgamedataeditor.common.widgets.casttype;

import sfgamedataeditor.database.objects.OffsetableObject;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.fieldwrapping.AbstractFieldListener;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CastTypeFormListener extends AbstractFieldListener<CastTypeWidget, OffsetableObject> implements ItemListener {

    private Map<JRadioButton, Pair<Integer, Integer>> radioButtonMapping = new HashMap<>();

    public CastTypeFormListener(CastTypeWidget widget, Field[] mappedField) {
        super(widget, mappedField);
        initializeRadioButtonsMapping();


        for (Map.Entry<JRadioButton, Pair<Integer, Integer>> jRadioButtonPairEntry : radioButtonMapping.entrySet()) {
            jRadioButtonPairEntry.getKey().addItemListener(this);
        }
    }

    private void initializeRadioButtonsMapping() {
        //        01 01 = any sort of projectile to enemy
        //        02 01 = any sort of projectile (including aura and wave) to player/allies/NPCs
        //        01 04 = target area effect (example: rain of fire)
        //        01 05 = instant area effect (example: pain area)
        //        03 05 = world instant area effect (example: raise dead)
        //        03 04 = world target area effect (example: fog)
        //        02 05 = player/allies/NPCs area effect (example: healing area)
        radioButtonMapping.put(getWidget().getIsProjectileToEnemyRadioButton(), new Pair<>(1, 1));
        radioButtonMapping.put(getWidget().getIsProjectileToAlliesRadioButton(), new Pair<>(2, 1));
        radioButtonMapping.put(getWidget().getIsTargetAreaRadioButton(), new Pair<>(1, 4));
        radioButtonMapping.put(getWidget().getIsInstantAreaRadioButton(), new Pair<>(1, 5));
        radioButtonMapping.put(getWidget().getIsWorldInstantAreaRadioButton(), new Pair<>(3, 5));
        radioButtonMapping.put(getWidget().getIsWorldTargetAreaRadioButton(), new Pair<>(3, 4));
        radioButtonMapping.put(getWidget().getIsAlliesAreaRadioButton(), new Pair<>(2, 5));
    }

    @Override
    protected int[] getFieldValues() {
        int value = 0;
        for (Map.Entry<JRadioButton, Pair<Integer, Integer>> jRadioButtonPairEntry : radioButtonMapping.entrySet()) {
            JRadioButton radioButton = jRadioButtonPairEntry.getKey();
            if (radioButton.isSelected()) {
                Pair<Integer, Integer> pair = jRadioButtonPairEntry.getValue();
                value = (pair.getKey() << 8) + pair.getValue();
                break;
            }
        }

        return new int[]{value};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int castType = value[0];
        // order of variables IS correct, cause in original file other byte order exists
        int secondValueEffect = castType & 0xFF;
        int firstValueEffect = (castType & 0xFF00) >> 8;

        Pair<Integer, Integer> valuePair = new Pair<>(firstValueEffect, secondValueEffect);
        for (Map.Entry<JRadioButton, Pair<Integer, Integer>> jRadioButtonPairEntry : radioButtonMapping.entrySet()) {
            if (jRadioButtonPairEntry.getValue().equals(valuePair)) {
                jRadioButtonPairEntry.getKey().setSelected(true);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            return;
        }

        Object source = e.getSource();
        for (Map.Entry<JRadioButton, Pair<Integer, Integer>> jRadioButtonPairEntry : radioButtonMapping.entrySet()) {
            JRadioButton radioButton = jRadioButtonPairEntry.getKey();
            if (!radioButton.equals(source)) {
                radioButton.setSelected(false);
            }
        }
    }
}
