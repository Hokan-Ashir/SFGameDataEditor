package sfgamedataeditor.common.viewconfigurations.unit.parameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidget;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidgetListener;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidgetListener;
import sfgamedataeditor.common.widgets.creatures.equipment.EquipmentWidget;
import sfgamedataeditor.common.widgets.creatures.equipment.EquipmentWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class UnitParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        addTextFieldWidgets();
        addEquipmentWidgets();
        addSpellEffectNumberWidgets();
    }

    private void addSpellEffectNumberWidgets() {
        ConfigurationWidgetParameter spells = new ConfigurationWidgetParameter(EffectNumberWidget.class, EffectNumberWidgetListener.class,
                I18NTypes.UNITS_GUI, "spells.spell.name");
        addViewMapping(GUIElements.SPELL1, spells);
        addViewMapping(GUIElements.SPELL2, spells);
        addViewMapping(GUIElements.SPELL3, spells);
    }

    private void addEquipmentWidgets() {
        ConfigurationWidgetParameter equipment = new ConfigurationWidgetParameter(EquipmentWidget.class, EquipmentWidgetListener.class,
                I18NTypes.UNITS_GUI, "equipment.slot.item.type", "equipment.slot.item.name");
        addViewMapping(GUIElements.HEAD_SLOT, equipment);
        addViewMapping(GUIElements.CHEST_SLOT, equipment);
        addViewMapping(GUIElements.RIGHT_HAND_SLOT, equipment);
        addViewMapping(GUIElements.LEFT_HAND_SLOT, equipment);
        addViewMapping(GUIElements.RIGHT_RING_SLOT, equipment);
        addViewMapping(GUIElements.LEFT_RING_SLOT, equipment);
        addViewMapping(GUIElements.LEGS_SLOT, equipment);
    }

    private void addTextFieldWidgets() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.EXPERIENCE, "experience");
            put(GUIElements.ARMOR, "armor");
            put(GUIElements.RESOURCE1, "resource");
            put(GUIElements.RESOURCE2, "resource");
            put(GUIElements.RESOURCE3, "resource");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.UNITS_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }
    }
}
