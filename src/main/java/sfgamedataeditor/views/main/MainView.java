package sfgamedataeditor.views.main;

import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.PostProcess;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.NullView;
import sfgamedataeditor.views.main.modules.common.ModulesMetaEvent;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;

public class MainView extends AbstractView<NullView> {
    private JPanel mainPanel;

    public MainView(NullView parentView) {
        super(parentView);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        EventHandlerRegister.INSTANCE.addEventHandler(new MainEventHandler());
        createAndShowMainFrame();
    }

    @PostProcess
    private void postProcess() {
        EventHandlerRegister.INSTANCE.fireEvent(new ModulesMetaEvent());
    }

    private void createAndShowMainFrame() {
        JFrame frame = new JFrame(I18N.INSTANCE.getMessage("sfmodFilesCreationWindowCaption"));
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ViewTools.centerFrame(frame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearAllComponents() {
        super.clearAllComponents();
        mainPanel.removeAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void repaint() {
        super.repaint();
        mainPanel.invalidate();
        mainPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChildView(AbstractView view) {
        super.addChildView(view);
        mainPanel.add(view.getMainPanel());
    }
}
