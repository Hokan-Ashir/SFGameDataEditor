package sfgamedataeditor.views.main;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.RenderableView;
import sfgamedataeditor.views.common.View;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;

public class MainView implements RenderableView {
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

    private void createAndShowMainFrame() {
        JFrame frame = new JFrame(I18N.INSTANCE.getMessage("sfmodFilesCreationWindowCaption"));
        frame.setResizable(false);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ViewTools.centerFrame(frame);
    }

    public void renderViewInsideContentPanel(View view) {
        renderViewInsidePanel(view, contentPanel);
    }

    public void renderViewInsideNavigationPanel(View view) {
        renderViewInsidePanel(view, navigationPanel);
    }

    public void renderViewInsideEventHistoryPanel(View view) {
        renderViewInsidePanel(view, eventHistoryPanel);
    }

    public void renderViewInsideButtonPanel(View view) {
        renderViewInsidePanel(view, buttonsPanel);
    }

    private void renderViewInsidePanel(View view, JPanel panel) {
        panel.add(view.getMainPanel());
        panel.revalidate();
        panel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void render() {

    }

    @Override
    public void unrender() {

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return null;
    }
}
