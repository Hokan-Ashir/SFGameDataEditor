package sfgamedataeditor.views.common;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.mvc.objects.SubModuleParameter;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class AbstractModulesPresenter<M extends SubModuleParameter, V extends AbstractModulesView, C extends Model> extends AbstractPresenter<M, V> {

    private ActionListener subPanelsListener = new PanelsListener();

    protected AbstractModulesPresenter(V view) {
        super(view);
    }

    protected abstract C createModel();

    @Override
    public void updateView() {
        updateSubViewsContent();
        getView().updateSubViewsLayout();
        List<SubViewPanel> subViewsPanels = getView().getSubViewsPanels();
        for (SubViewPanel subViewsPanel : subViewsPanels) {
            subViewsPanel.getButton().addActionListener(subPanelsListener);
        }

        Model<M> model = getModel();
        if (model == null) {
            getView().setSelectedModuleValue(null);
        } else {
            String selectedModuleName = model.getParameter().getSelectedModuleName();
            getView().setSelectedModuleValue(selectedModuleName);
        }
    }

    protected abstract void updateSubViewsContent();

    @Override
    public void renderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);

        JPanel managerPanel = getView().getPanelManager().getMainPanel();
        mainView.renderViewInsideContentPanel(managerPanel);

        JButton selectedPanel = getView().getSelectedPanel();
        mainView.renderViewInsideNavigationPanel(selectedPanel);
    }

    @Override
    public void unRenderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);

        JPanel managerPanel = getView().getPanelManager().getMainPanel();
        mainView.unRenderViewInsideContentPanel(managerPanel);

        JButton selectedPanel = getView().getSelectedPanel();
        mainView.unRenderViewInsideNavigationPanel(selectedPanel);
    }

    private final class PanelsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            Class<? extends PresentableView> classViewToShow = getView().getSubPanelViewClass((JButton) source);
            Model model = createModel();
            EventProcessor.INSTANCE.process(new ShowContentViewEvent(classViewToShow, model));
        }
    }
}
