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
import java.util.Set;

public abstract class AbstractModulesPresenter<M extends SubModuleParameter, V extends AbstractModulesView, C extends Model> extends AbstractPresenter<M, V> {

    private final ActionListener subPanelsListener = new PanelsListener();

    protected AbstractModulesPresenter(V view) {
        super(view);
        getView().getSelectedPanel().addActionListener(subPanelsListener);
    }

    protected abstract C createModel();

    @Override
    public void updateView() {
        Model<M> model = getModel();
        if (model != null) {
            Set<String> subPanelsNames = model.getParameter().getSubPanelsNames();
            if (subPanelsNames != null) {
                getView().addMappings(subPanelsNames, model.getParameter().getSubPanelsViewClass());
            }
        }

        getView().updateSubViewsLayout();
        List<SubViewPanel> subViewsPanels = getView().getSubViewsPanels();
        for (SubViewPanel subViewsPanel : subViewsPanels) {
            if (subViewsPanel.getButton().getActionListeners().length == 0) {
                subViewsPanel.getButton().addActionListener(subPanelsListener);
            }
        }

        if (model != null) {
            String selectedModuleName = model.getParameter().getSelectedModuleName();
            if (selectedModuleName != null) {
                getView().setSelectedModuleValue(selectedModuleName);
            } else {
                getView().setSelectedModuleValue(getView().getModuleName());
            }
        } else {
            getView().setSelectedModuleValue(getView().getModuleName());
        }
    }

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
            JButton source = (JButton) e.getSource();

            if (source.getText().equals(getView().getModuleName())) {
                return;
            }

            getView().setSelectedModuleValue(source.getText());
            getView().setSelectedModuleIcon(source.getIcon());
            Class<? extends PresentableView> classViewToShow = getView().getSubPanelViewClass(source);
            Model model;
            if (source.equals(getView().getSelectedPanel())) {
                model = getModel();
            } else {
                model = createModel();
            }

            EventProcessor.INSTANCE.process(new ShowContentViewEvent(classViewToShow, model));
        }
    }
}
