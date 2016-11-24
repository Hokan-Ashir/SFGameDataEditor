package sfgamedataeditor.common.widgets;

import sfgamedataeditor.fieldwrapping.AbstractFieldListener;

import javax.swing.*;

public abstract class AbstractWidget<T extends AbstractFieldListener> extends JPanel {
    private AbstractFieldListener listener;

    public void setListener(AbstractFieldListener listener) {
        this.listener = listener;
    }

    public AbstractFieldListener getListener() {
        return listener;
    }

    public void attachListener(T listener) {
        insertListener(listener);
        this.listener = listener;
    }

    protected abstract void insertListener(T listener);
    public abstract void updateI18N();
    public abstract JPanel getMainPanel();
}
