package sfgamedataeditor.views.common;

import org.apache.log4j.Logger;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.managers.DefaultModulesPanelManager;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModulesView implements PresentableView {

    private static final Logger LOGGER = Logger.getLogger(AbstractModulesView.class);
    private JButton selectedPanel = new JButton();
    private List<SubViewPanel> subViewsPanels = new ArrayList<>();
    private DefaultModulesPanelManager panelManager;

    protected AbstractModulesView(String viewName) {
        Class<? extends DefaultModulesPanelManager> managerClass = getModulesPanelManagerClass();
        try {
            panelManager = managerClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        initializeSubViewsMapping();
    }

    protected abstract void fillComboBoxMapping();

    private void initializeSubViewsMapping() {
        // TODO optimize adding objects to list without clearing it
        subViewsPanels.clear();
        fillComboBoxMapping();
    }

    public void updateSubViewsLayout() {
        panelManager.updatePanelsLayout(subViewsPanels);
    }

    // TODO add Image mapping to button
    public void addMapping(String name, Class<? extends PresentableView> subViewClass) {
        subViewsPanels.add(new SubViewPanel(new JButton(name), subViewClass));
    }

    public void setSelectedModuleValue(String selectedModuleValue) {
        selectedPanel.setText(selectedModuleValue);
    }

    public String getSelectedModuleValue() {
        return selectedPanel.getText();
    }

    // TODO replace respectively in specific views and cases
    private Class<? extends DefaultModulesPanelManager> getModulesPanelManagerClass() {
        return DefaultModulesPanelManager.class;
    }

    public JButton getSelectedPanel() {
        return selectedPanel;
    }

    public DefaultModulesPanelManager getPanelManager() {
        return panelManager;
    }

    public List<SubViewPanel> getSubViewsPanels() {
        return subViewsPanels;
    }

    public Class<? extends PresentableView> getSubPanelViewClass(JButton clickedButton) {
        for (SubViewPanel subViewsPanel : subViewsPanels) {
            if (subViewsPanel.getButton().equals(clickedButton)) {
                return subViewsPanel.getSubViewClass();
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return null;
    }
}
