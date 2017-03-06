package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersModel;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersView;

import java.lang.reflect.Field;

public class WorkerRunesLevelComboBoxListener extends AbstractLevelComboBoxListener {

    public WorkerRunesLevelComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
        super(widget, mappedField);
    }

    @Override
    protected void processSelectedItemEvent(String selectedItem) {
        Model oldModel = ViewRegister.INSTANCE.getViews().get(WorkersRunesParametersView.class).getPresenter().getModel();
        ((WorkersRunesParametersModel) oldModel).getParameter().setRuneLevel(Integer.valueOf(selectedItem));
        ShowContentViewEvent event = new ShowContentViewEvent(WorkersRunesParametersView.class, oldModel);
        EventProcessor.INSTANCE.process(event);
    }
}
