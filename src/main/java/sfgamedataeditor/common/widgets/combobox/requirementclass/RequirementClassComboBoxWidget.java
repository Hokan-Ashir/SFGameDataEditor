package sfgamedataeditor.common.widgets.combobox.requirementclass;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.mvc.objects.Model;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.EventListener;

public class RequirementClassComboBoxWidget extends AbstractWidget {
    private JPanel mainPanel;
    private JComboBox comboBox;
    private JLabel label;

    public RequirementClassComboBoxWidget(Field DTOField) {
        super(DTOField);
        ClassRequirementComboBoxListener comboBoxListener = new ClassRequirementComboBoxListener(comboBox, DTOField);
        comboBox.addItemListener(comboBoxListener);
    }

    @Override
    public void attachListener(EventListener listener) {

    }

    @Override
    public void update(Model model) {

    }

    @Override
    public void updateI18N() {

    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
