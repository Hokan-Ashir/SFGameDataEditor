package sfgamedataeditor.views.main;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.main.modules.ButtonsView;
import sfgamedataeditor.views.main.modules.ModulesView;
import sfgamedataeditor.views.main.modules.ShowButtonsViewEvent;
import sfgamedataeditor.views.main.modules.ShowModulesViewEvent;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;

public class MainView extends AbstractView {
    private JPanel mainPanel;

    public MainView(AbstractView parentView) {
        super(parentView);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        EventHandlerRegister.INSTANCE.addEventHandler(new MainEventHandler());
    }

    public static void showMainView() {
        JFrame frame = new JFrame(I18N.INSTANCE.getMessage("sfmodFilesCreationWindowCaption"));
        final MainView view = new MainView(null);
        frame.setContentPane(view.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ViewTools.centerFrame(frame);
        showViews(view);
    }

    private static void showViews(MainView view) {
        showModulesView(view);
        showButtonsView(view);
    }

    private static void showModulesView(MainView view) {
        ClassTuple tuple = new ClassTuple<>(ModulesView.class, view);
        ShowModulesViewEvent event = new ShowModulesViewEvent(tuple);
        EventHandlerRegister.INSTANCE.fireEvent(event);
    }

    private static void showButtonsView(MainView view) {
        ClassTuple tuple = new ClassTuple<>(ButtonsView.class, view);
        ShowButtonsViewEvent event = new ShowButtonsViewEvent(tuple);
        EventHandlerRegister.INSTANCE.fireEvent(event);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void clearAllComponents() {
        super.clearAllComponents();
        mainPanel.removeAll();
    }

    @Override
    public void repaint() {
        super.repaint();
        mainPanel.invalidate();
        mainPanel.repaint();
    }

    @Override
    public void addChildView(AbstractView view) {
        super.addChildView(view);
        mainPanel.add(view.getMainPanel());
    }
}
