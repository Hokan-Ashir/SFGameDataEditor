package sfgamedataeditor.common.viewconfigurations.objects.chests;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidgetListener;
import sfgamedataeditor.common.widgets.creatures.equipment.EquipmentWidget;
import sfgamedataeditor.common.widgets.creatures.equipment.EquipmentWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class ChestLootParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        addTextFieldWidgets();
        addEquipmentWidgets();
    }

    private void addEquipmentWidgets() {
        ConfigurationWidgetParameter equipment = new ConfigurationWidgetParameter(EquipmentWidget.class, EquipmentWidgetListener.class,
                I18NTypes.CREATURES_GUI, "equipment.slot.item.type", "equipment.slot.item.name");
        addViewMapping(GUIElements.DROP_ITEM_1, equipment);
        addViewMapping(GUIElements.DROP_ITEM_2, equipment);
        addViewMapping(GUIElements.DROP_ITEM_3, equipment);
    }

    private void addTextFieldWidgets() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.DROP_POSSIBILITY_ITEM_1, "loot.drop.possibility");
            put(GUIElements.DROP_POSSIBILITY_ITEM_2, "loot.drop.possibility");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.CREATURES_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }
    }
}
