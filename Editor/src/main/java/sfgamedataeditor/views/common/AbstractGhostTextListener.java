package sfgamedataeditor.views.common;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// taken from http://stackoverflow.com/questions/10506789/how-to-display-faint-gray-ghost-text-in-a-jtextfield
public abstract class AbstractGhostTextListener  implements FocusListener, DocumentListener, PropertyChangeListener {
    private static final String FOREGROUND_PROPERTY_NAME = "foreground";
    private final JTextField textfield;
    private boolean isEmpty;
    private final Color ghostColor;
    private Color foregroundColor;
    private final String ghostText;

    public AbstractGhostTextListener(final JTextField textfield, String ghostText) {
        super();
        this.textfield = textfield;
        this.ghostText = ghostText;
        this.ghostColor = Color.LIGHT_GRAY;
        textfield.addFocusListener(this);
        registerListeners();
        updateState();
        if (!this.textfield.hasFocus()) {
            focusLost(null);
        }
    }

    private void registerListeners() {
        textfield.getDocument().addDocumentListener(this);
        textfield.addPropertyChangeListener(FOREGROUND_PROPERTY_NAME, this);
    }

    private void unregisterListeners() {
        textfield.getDocument().removeDocumentListener(this);
        textfield.removePropertyChangeListener(FOREGROUND_PROPERTY_NAME, this);
    }

    private void updateState() {
        isEmpty = textfield.getText().length() == 0;
        foregroundColor = textfield.getForeground();

        invokeUpdateStateActions();
        textfield.grabFocus();
    }

    protected abstract void invokeUpdateStateActions();

    @Override
    public void focusGained(FocusEvent e) {
        if (!isEmpty) {
            return;
        }

        unregisterListeners();
        try {
            textfield.setText("");
            textfield.setForeground(foregroundColor);
        } finally {
            registerListeners();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (!isEmpty) {
            return;
        }

        unregisterListeners();
        try {
            textfield.setText(ghostText);
            textfield.setForeground(ghostColor);
        } finally {
            registerListeners();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateState();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateState();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateState();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateState();
    }

}
