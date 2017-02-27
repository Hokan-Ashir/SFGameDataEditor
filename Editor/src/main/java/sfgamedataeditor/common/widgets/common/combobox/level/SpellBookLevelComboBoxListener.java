package sfgamedataeditor.common.widgets.common.combobox.level;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.main.modules.spellbook.parameters.SpellBookParametersModel;
import sfgamedataeditor.views.main.modules.spellbook.parameters.SpellBookParametersView;

import java.lang.reflect.Field;

public class SpellBookLevelComboBoxListener extends AbstractLevelComboBoxListener {

    public SpellBookLevelComboBoxListener(LevelComboBoxWidget widget, Field[] mappedField) {
        super(widget, mappedField);
    }

    @Override
    protected void processSelectedItemEvent(String selectedItem) {
        Model oldModel = ViewRegister.INSTANCE.getViews().get(SpellBookParametersView.class).getController().getModel();
        ((SpellBookParametersModel) oldModel).getParameter().setScrollLevel(Integer.valueOf(selectedItem));
        ShowContentViewEvent event = new ShowContentViewEvent(SpellBookParametersView.class, oldModel);
        EventProcessor.INSTANCE.process(event);
    }
}
