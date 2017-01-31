package sfgamedataeditor.views.main;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class MainView implements PresentableView {
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
        JFrame frame = new JFrame(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "sfmodFilesCreationWindowCaption"));
        frame.setResizable(false);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ViewTools.centerFrame(frame);
    }

    public void renderViewInsideContentPanel(JComponent viewComponent) {
        contentPanel.removeAll();
        renderViewInsidePanel(viewComponent, contentPanel);
    }

    public void renderViewInsideNavigationPanel(JComponent viewComponent) {
        renderViewInsidePanel(viewComponent, navigationPanel);
    }

    public void renderViewInsideEventHistoryPanel(JComponent viewComponent) {
        renderViewInsidePanel(viewComponent, eventHistoryPanel);
    }

    public void renderViewInsideButtonPanel(JComponent viewComponent) {
        renderViewInsidePanel(viewComponent, buttonsPanel);
    }

    private void renderViewInsidePanel(JComponent viewComponent, JPanel panel) {
        panel.add(viewComponent);
        panel.revalidate();
        panel.repaint();
    }

    public void unRenderViewInsideContentPanel(JComponent viewComponent) {
        unRenderViewInsidePanel(viewComponent, contentPanel);
    }

    public void unRenderViewInsideNavigationPanel(JComponent viewComponent) {
        unRenderViewInsidePanel(viewComponent, navigationPanel);
    }

    private void unRenderViewInsidePanel(JComponent viewComponent, JPanel panel) {
        panel.remove(viewComponent);
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
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return null;
    }
}
