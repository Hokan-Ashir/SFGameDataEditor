package sfgamedataeditor.common.widgets;

import javax.swing.*;
import java.util.List;

public abstract class AbstractWidget<L extends AbstractWidgetListener> extends JPanel {
    private AbstractWidgetListener listener;

    public void setListener(AbstractWidgetListener listener) {
        this.listener = listener;
    }

    public AbstractWidgetListener getListener() {
        return listener;
    }

    public void attachListener(L listener) {
        insertListener(listener);
        this.listener = listener;
    }

    protected abstract void insertListener(L listener);
    public abstract void updateI18N(List<String> i18nStrings);
    public abstract JPanel getMainPanel();
}
