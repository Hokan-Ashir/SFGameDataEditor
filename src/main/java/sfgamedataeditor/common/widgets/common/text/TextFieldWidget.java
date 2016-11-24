package sfgamedataeditor.common.widgets.common.text;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;

public class TextFieldWidget extends AbstractWidget<TextFieldListener> {
    private JPanel mainPanel;
    private JTextField field;
    private JLabel label;

    public TextFieldWidget() {
        add(getMainPanel());
    }

    public JTextField getField() {
        return field;
    }

    @Override
    public void insertListener(TextFieldListener listener) {
        field.getDocument().addDocumentListener(listener);
    }

    @Override
    public void updateI18N() {

    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void setEnabled(boolean enabled) {
        field.setEnabled(enabled);
    }
}
