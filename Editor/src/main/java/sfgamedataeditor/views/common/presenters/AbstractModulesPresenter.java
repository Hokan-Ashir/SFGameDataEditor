package sfgamedataeditor.views.common.presenters;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.ShowContentViewEvent;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.SubViewPanel;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.ModulesView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class AbstractModulesPresenter<P extends AbstractSubModuleParameter, V extends AbstractModulesView, C extends Model> extends AbstractPresenter<P, V> {

    private final ActionListener subPanelsListener = new PanelsListener();

    protected AbstractModulesPresenter(V view) {
        super(view);
        getView().getSelectedPanelButton().addActionListener(subPanelsListener);
    }

    protected abstract C createModel();

    @Override
    public void updateView() {
        Model<P> model = getModel();
        if (model != null) {
            List<ObjectTuple> subPanelsNames = model.getParameter().getSubViewPanelTuples();
            if (subPanelsNames != null) {
                getView().addMappings(subPanelsNames, model.getParameter().getSubPanelsViewClass());
            } else {
                getView().fillSubViewsMappings();
            }
        } else {
            getView().fillSubViewsMappings();
        }

        getView().updateSubViewsLayout();
        List<SubViewPanel> subViewsPanels = getView().getSubViewsPanels();
        for (SubViewPanel subViewsPanel : subViewsPanels) {
            if (subViewsPanel.getButton().getActionListeners().length == 0) {
                subViewsPanel.getButton().addActionListener(subPanelsListener);
            }
        }

        if (model != null) {
            ObjectTuple selectedModuleName = model.getParameter().getSelectedModuleName();
            if (selectedModuleName != null && selectedModuleName.getName() != null) {
                getView().setSelectedModuleValue(selectedModuleName.getName());
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

        JPanel managerPanel = getView().getManagerPanel();
        mainView.renderViewInsideContentPanel(managerPanel);

        JPanel selectedPanel = getView().getSelectedPanel();
        mainView.renderViewInsideNavigationPanel(selectedPanel);
    }

    @Override
    public void unRenderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);

        JPanel managerPanel = getView().getManagerPanel();
        mainView.unRenderViewInsideContentPanel(managerPanel);

        JPanel selectedPanel = getView().getSelectedPanel();
        mainView.unRenderViewInsideNavigationPanel(selectedPanel);
    }

    private final class PanelsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();

            if (source.getText().equals(getView().getModuleName())) {
                return;
            }

            Class<? extends PresentableView> classViewToShow = getView().getSubPanelViewClass(source);
            Integer subPanelViewObjectId = getView().getSubPanelViewObjectId(source);
            getView().setSelectedObjectId(subPanelViewObjectId);
            Model model;
            if (source.getText().equals(getView().getSelectedPanelButton().getText()) && !(getView().getClass().equals(ModulesView.class))) {
                getView().setSelectedModuleValue(source.getText());
                model = getModel();
            } else {
                getView().setSelectedModuleValue(source.getText());
                model = createModel();
            }

            EventProcessor.INSTANCE.process(new ShowContentViewEvent(classViewToShow, model));
        }
    }
}
