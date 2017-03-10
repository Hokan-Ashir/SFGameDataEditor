package sfgamedataeditor.common.widgets.common.combobox.requirementclass;

import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class SpellRequirementClassSubClassWidgetListener extends AbstractRequirementClassSubClassWidgetListener {

    public SpellRequirementClassSubClassWidgetListener(RequirementClassSubClassWidget widget, Field[] DTOField) {
        super(widget, DTOField);
    }

    @Override
    protected void fillWidgetClassComboBox() {
        JComboBox<String> comboBox = getWidget().getRequirementClassComboBox();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.SKILL_SCHOOL_MAPPING);
        List<String> skillNames = new ArrayList<>();
        for (String key : bundle.keySet()) {
            skillNames.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, key));
        }

        Collections.sort(skillNames);
        for (String skillName : skillNames) {
            comboBox.addItem(skillName);
        }
    }

    @Override
    protected void fillSubClassComboBox(String selectedSkillClass) {
        String skillClassKey = ViewTools.getKeyStringByPropertyValue(selectedSkillClass, I18NTypes.COMMON);
        if (skillClassKey.equals("other")) {
            return;
        }

        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.SKILL_SUB_SCHOOL_MAPPING);
        JComboBox<String> comboBox = getWidget().getRequirementSubClassComboBox();
        comboBox.addItem("");
        for (String key : bundle.keySet()) {
            if (key.startsWith(skillClassKey)) {
                String value = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, key);
                comboBox.addItem(value);
            }
        }
    }
}
