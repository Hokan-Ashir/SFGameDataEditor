package sfgamedataeditor.common.widgets.common.text;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.mvc.objects.Model;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.EventListener;

public class TextFieldWidget extends AbstractWidget {
    private JPanel mainPanel;
    private JTextField field;
    private JLabel label;

    public TextFieldWidget(Field DTOField) {
        super(DTOField);
        TextFieldListener listener = new TextFieldListener(field, DTOField);
        field.getDocument().addDocumentListener(listener);
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
