package sfgamedataeditor.common.widgets.textfield;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;
import java.util.List;

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

    // TODO get rid of this bullshit
    @Override
    public void updateI18N(List<String> i18nStrings) {
        if (i18nStrings.isEmpty()) {
            setVisible(false);
        } else {
            setVisible(true);
            label.setText(i18nStrings.get(0));
        }
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
