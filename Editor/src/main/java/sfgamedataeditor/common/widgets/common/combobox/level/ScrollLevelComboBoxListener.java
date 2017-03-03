package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersModel;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.parameters.SpellScrollsParametersView;

import java.lang.reflect.Field;

public class ScrollLevelComboBoxListener extends AbstractLevelComboBoxListener {

    public ScrollLevelComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
        super(widget, mappedField);
    }

    @Override
    protected void processSelectedItemEvent(String selectedItem) {
        Model oldModel = ViewRegister.INSTANCE.getViews().get(SpellScrollsParametersView.class).getPresenter().getModel();
        ((SpellScrollsParametersModel) oldModel).getParameter().setScrollLevel(Integer.valueOf(selectedItem));
        ShowContentViewEvent event = new ShowContentViewEvent(SpellScrollsParametersView.class, oldModel);
        EventProcessor.INSTANCE.process(event);
    }
}
