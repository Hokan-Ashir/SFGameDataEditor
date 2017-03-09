package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersView;

import java.lang.reflect.Field;

public class WorkerRunesLevelComboBoxListener extends AbstractLevelComboBoxListener {

    public WorkerRunesLevelComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
        super(widget, mappedField);
    }

    @Override
    protected Class<? extends PresentableView> getViewClass() {
        return WorkersRunesParametersView.class;
    }
}
