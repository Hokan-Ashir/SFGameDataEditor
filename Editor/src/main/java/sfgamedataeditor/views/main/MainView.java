package sfgamedataeditor.views.main;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class MainView implements PresentableView {
    private static final int VERTICAL_SCROLL_UNIT_INCREMENT = 16;
    private JPanel mainPanel;
    private JPanel navigationPanel;
    private JPanel contentPanel;
    private JPanel headerPanel;
    private JPanel buttonsPanel;
    private JScrollPane scrollPane;
    private JPanel componentsPanel;

    public MainView() {
        scrollPane.getVerticalScrollBar().setUnitIncrement(VERTICAL_SCROLL_UNIT_INCREMENT);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));
        createAndShowMainFrame();
    }

    private void createAndShowMainFrame() {
        JFrame frame = new JFrame(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "sfmodFilesCreationWindowCaption"));
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        GraphicsEnvironment env =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        frame.setMaximizedBounds(env.getMaximumWindowBounds());
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();
    }

    public void renderViewInsideContentPanel(JComponent viewComponent) {
        contentPanel.removeAll();
        renderViewInsidePanel(viewComponent, contentPanel);
    }

    public void renderViewInsideNavigationPanel(JComponent viewComponent) {
        renderViewInsidePanel(viewComponent, navigationPanel);
    }

    public void renderViewInsideHeaderPanel(JComponent viewComponent) {
        renderViewInsidePanel(viewComponent, headerPanel);
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
        // because Swing with get TreeLock and send numerous amount of notifications to its
        // render engine, before removal we can make component invisible, remove components
        // and make it visible again
        panel.setVisible(false);
        panel.remove(viewComponent);
        panel.setVisible(true);
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
