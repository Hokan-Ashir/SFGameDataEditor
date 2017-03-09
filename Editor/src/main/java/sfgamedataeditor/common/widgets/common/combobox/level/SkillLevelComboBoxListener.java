package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;

import java.lang.reflect.Field;

public class SkillLevelComboBoxListener extends AbstractLevelComboBoxListener {

    public SkillLevelComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
        super(widget, mappedField);
    }

    @Override
    protected Class<? extends PresentableView> getViewClass() {
        return SkillParameterView.class;
    }
}
