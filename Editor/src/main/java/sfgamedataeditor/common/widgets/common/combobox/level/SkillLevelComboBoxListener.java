package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterModel;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;

import java.lang.reflect.Field;

public class SkillLevelComboBoxListener extends AbstractLevelComboBoxListener {

    public SkillLevelComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
        super(widget, mappedField);
    }

    @Override
    protected void processSelectedItemEvent(String selectedItem) {
        Model oldModel = ViewRegister.INSTANCE.getViews().get(SkillParameterView.class).getPresenter().getModel();
        ((SkillParameterModel) oldModel).getParameter().setSkillLevel(Integer.valueOf(selectedItem));
        ShowContentViewEvent event = new ShowContentViewEvent(SkillParameterView.class, oldModel);
        EventProcessor.INSTANCE.process(event);
    }
}
