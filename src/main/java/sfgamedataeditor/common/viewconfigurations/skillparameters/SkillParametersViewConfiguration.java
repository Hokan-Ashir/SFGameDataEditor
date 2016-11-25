package sfgamedataeditor.common.viewconfigurations.skillparameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.common.widgets.combobox.level.LevelComboBoxListener;
import sfgamedataeditor.common.widgets.combobox.level.LevelComboBoxWidget;
import sfgamedataeditor.common.widgets.textfield.TextFieldListener;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidget;
import sfgamedataeditor.databind.Pair;

public class SkillParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        addViewMapping(GUIElements.STRENGTH, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.STAMINA, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.AGILITY, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.DEXTERITY, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.CHARISMA, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.INTELLIGENCE, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.WISDOM, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.SKILL_LEVEL, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>>(LevelComboBoxWidget.class, LevelComboBoxListener.class));
    }
}
