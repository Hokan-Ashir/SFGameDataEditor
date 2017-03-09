package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;

import java.lang.reflect.Field;

public class ScrollLevelComboBoxListener extends AbstractLevelComboBoxListener {

    public ScrollLevelComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
        super(widget, mappedField);
    }

    @Override
    protected Class<? extends PresentableView> getViewClass() {
        return SpellScrollsParametersView.class;
    }
}
