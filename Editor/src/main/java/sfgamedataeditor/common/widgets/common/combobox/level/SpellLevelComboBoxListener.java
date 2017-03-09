package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.lang.reflect.Field;

public class SpellLevelComboBoxListener extends AbstractLevelComboBoxListener {

    public SpellLevelComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
        super(widget, mappedField);
    }

    @Override
    protected Class<? extends PresentableView> getViewClass() {
        return SpellParameterView.class;
    }
}
