package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.lang.reflect.Field;

public class SpellLevelComboBoxListener extends AbstractLevelComboBoxListener {

    public SpellLevelComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
        super(widget, mappedField);
    }

    @Override
    protected void processSelectedItemEvent(String selectedItem) {
        Model oldModel = ViewRegister.INSTANCE.getViews().get(SpellParameterView.class).getPresenter().getModel();
        ((SpellParameterModel) oldModel).getParameter().setSpellLevel(Integer.valueOf(selectedItem));
        ShowContentViewEvent event = new ShowContentViewEvent(SpellParameterView.class, oldModel);
        EventProcessor.INSTANCE.process(event);
    }
}
