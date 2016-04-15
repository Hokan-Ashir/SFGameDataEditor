package sfgamedataeditor.views.common;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.ModulesView;

import javax.swing.*;

public class NotImplementedView extends AbstractView<ModulesView> {
    private JLabel notImplementedLabel;
    private JPanel mainPanel;

    public NotImplementedView(ModulesView parentView) {
        super(parentView);
        notImplementedLabel.setText(I18N.INSTANCE.getMessage("notImplemented"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        getParentView().getSubModulesPanel().removeAll();
        getParentView().getSubModulesPanel().add(mainPanel);
        getParentView().getSubModulesPanel().revalidate();
        getParentView().getSubModulesPanel().repaint();
    }
}
