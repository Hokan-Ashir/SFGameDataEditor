package sfgamedataeditor.common.widgets.combobox.requirementclass;

import sfgamedataeditor.common.widgets.combobox.AbstractComboBoxWidget;
import sfgamedataeditor.mvc.objects.Model;

import java.lang.reflect.Field;

public class RequirementClassComboBoxWidget extends AbstractComboBoxWidget {
    public RequirementClassComboBoxWidget(Field DTOField) {
        super(DTOField);
        getComboBox().addItemListener(new ClassRequirementComboBoxListener(getComboBox(), DTOField));
    }

    @Override
    public void update(Model model) {

    }

    @Override
    public void updateI18N() {

    }
}
