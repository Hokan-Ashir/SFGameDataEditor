package sfgamedataeditor.views.main;

import sfgamedataeditor.events.PostProcess;
import sfgamedataeditor.mvc.ShowViewDispatcher;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.main.modules.common.buttons.ButtonsView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;

public class MainView extends AbstractView {
    private JPanel mainPanel;
    private JPanel navigationPanel;
    private JPanel contentPanel;
    private JPanel eventHistoryPanel;
    private JPanel buttonsPanel;
    private JScrollPane scrollPane;
    private JPanel componentsPanel;

    public MainView() {
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));
        createAndShowMainFrame();
    }

    @PostProcess
    private void postProcess() {
        ShowViewDispatcher.INSTANCE.showView(EventHistoryView.class, null);
        ShowViewDispatcher.INSTANCE.showView(ModulesView.class, null);
        ShowViewDispatcher.INSTANCE.showView(ButtonsView.class, null);
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
}
