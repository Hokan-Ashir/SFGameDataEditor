package sfgamedataeditor.common.widgets.textfield;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TextFieldWidget extends AbstractWidget<TextFieldWidgetListener> {
    private static final int INSETS_VALUE = 5;
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
    public void insertListener(TextFieldWidgetListener listener) {
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
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        mainPanel.setPreferredSize(preferredSize);

        field.setPreferredSize(new Dimension(preferredSize.width, preferredSize.height / 2 - INSETS_VALUE));
        label.setPreferredSize(new Dimension(preferredSize.width, preferredSize.height / 2 - INSETS_VALUE));
    }

    @Override
    public void setMinimumSize(Dimension minimumSize) {
        super.setMinimumSize(minimumSize);
        mainPanel.setMinimumSize(minimumSize);

        field.setMinimumSize(new Dimension(minimumSize.width, minimumSize.height / 2 - INSETS_VALUE));
        label.setMinimumSize(new Dimension(minimumSize.width, minimumSize.height / 2 - INSETS_VALUE));
    }

    @Override
    public void setMaximumSize(Dimension maximumSize) {
        super.setMaximumSize(maximumSize);
        mainPanel.setMaximumSize(maximumSize);

        field.setMaximumSize(new Dimension(maximumSize.width, maximumSize.height / 2 - INSETS_VALUE));
        label.setMaximumSize(new Dimension(maximumSize.width, maximumSize.height / 2 - INSETS_VALUE));
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        label.setVisible(aFlag);
        field.setVisible(aFlag);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        field.setEnabled(enabled);
    }
}
