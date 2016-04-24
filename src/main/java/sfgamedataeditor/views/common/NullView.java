package sfgamedataeditor.views.common;

import javax.swing.*;

public class NullView extends AbstractView {

    public NullView(AbstractView parentView) {
        super(parentView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return null;
    }
}
