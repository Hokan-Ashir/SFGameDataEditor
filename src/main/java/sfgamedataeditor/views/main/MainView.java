package sfgamedataeditor.views.main;

import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.PostProcess;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.NullView;
import sfgamedataeditor.views.main.modules.common.ModulesMetaEvent;
import sfgamedataeditor.views.main.modules.common.buttons.ButtonsView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;

public class MainView extends AbstractView<NullView> {
    private JPanel mainPanel;
    private JPanel navigationPanel;
    private JPanel contentPanel;
    private JPanel eventHistoryPanel;
    private JPanel buttonsPanel;
    private JScrollPane scrollPane;
    private JPanel componentsPanel;

    public MainView(NullView parentView) {
        super(parentView);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));
        EventHandlerRegister.INSTANCE.addEventHandler(new MainEventHandler());
        createAndShowMainFrame();
    }

    @PostProcess
    private void postProcess() {
        EventHandlerRegister.INSTANCE.fireEvent(new ModulesMetaEvent());
    }

    private void createAndShowMainFrame() {
        JFrame frame = new JFrame(I18N.INSTANCE.getMessage("sfmodFilesCreationWindowCaption"));
        frame.setResizable(false);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        eventHistoryPanel.removeAll();
        navigationPanel.removeAll();
        contentPanel.removeAll();
        buttonsPanel.removeAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void repaint() {
        super.repaint();
        eventHistoryPanel.revalidate();
        eventHistoryPanel.repaint();

        navigationPanel.revalidate();
        navigationPanel.repaint();

        contentPanel.revalidate();
        contentPanel.repaint();

        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChildView(AbstractView view) {
        super.addChildView(view);
        // TODO get rid of instance of
        if (view instanceof EventHistoryView) {
            eventHistoryPanel.add(view.getMainPanel());
        } else if (view instanceof AbstractModulesView) {
            navigationPanel.add(view.getMainPanel());
        } else if (view instanceof ButtonsView){
            buttonsPanel.add(view.getMainPanel());
        } else {
            contentPanel.add(view.getMainPanel());
        }
    }
}
