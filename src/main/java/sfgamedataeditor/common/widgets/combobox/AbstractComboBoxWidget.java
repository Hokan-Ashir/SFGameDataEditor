package sfgamedataeditor.common.widgets.combobox;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;
import java.lang.reflect.Field;

public abstract class AbstractComboBoxWidget<T> extends AbstractWidget<T> {
    private JPanel mainPanel;
    private JComboBox<String> comboBox;
    private JLabel label;

    public AbstractComboBoxWidget(Field DTOField) {
        super(DTOField);
    }

    protected JComboBox<String> getComboBox() {
        return comboBox;
    }

    protected JLabel getLabel() {
        return label;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
