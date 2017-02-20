package sfgamedataeditor.common.viewconfigurations.buildings.parameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidgetListener;
import sfgamedataeditor.common.widgets.creatures.races.RacesWidget;
import sfgamedataeditor.common.widgets.creatures.races.RacesWidgetListener;
import sfgamedataeditor.common.widgets.units.ResourceWidget;
import sfgamedataeditor.common.widgets.units.ResourceWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingsParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        addTextFieldWidgets();
        addRaceWidgets();
        addResourceTypeWidgets();
    }

    private void addResourceTypeWidgets() {
        List<Integer> resourceIds = new ArrayList<Integer>() {{
            add(GUIElements.RESOURCE_1);
            add(GUIElements.RESOURCE_2);
            add(GUIElements.RESOURCE_3);
        }};

        for (Integer resourceId : resourceIds) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(ResourceWidget.class, ResourceWidgetListener.class, I18NTypes.BUILDING_GUI);
            addViewMapping(resourceId, parameter);
        }
    }

    private void addRaceWidgets() {
        ConfigurationWidgetParameter race = new ConfigurationWidgetParameter(RacesWidget.class, RacesWidgetListener.class, I18NTypes.BUILDING_GUI,
                "race");
        addViewMapping(GUIElements.RACE, race);
    }

    private void addTextFieldWidgets() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.HP, "hp");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.BUILDING_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }
    }
}
