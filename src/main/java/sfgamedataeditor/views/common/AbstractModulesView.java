package sfgamedataeditor.views.common;

import org.apache.log4j.Logger;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.managers.DefaultModulesPanelManager;
import sfgamedataeditor.views.utility.Pair;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class AbstractModulesView implements PresentableView {

    private static final Logger LOGGER = Logger.getLogger(AbstractModulesView.class);
    private JButton selectedPanel = new JButton();
    private List<SubViewPanel> subViewsPanels = new ArrayList<>();
    private Comparator<SubViewPanel> subViewPanelComparator = new SubViewsPanelComparator();
    private DefaultModulesPanelManager panelManager;
    private final String moduleName;

    protected AbstractModulesView(String viewName) {
        this.moduleName = viewName;

        Class<? extends DefaultModulesPanelManager> managerClass = getModulesPanelManagerClass();
        try {
            panelManager = managerClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        initializeSubViewsMapping();
    }

    protected abstract void fillSubViewsMappings();

    private void initializeSubViewsMapping() {
        // TODO optimize adding objects to list without clearing it
        fillSubViewsMappings();
    }

    public void updateSubViewsLayout() {
        panelManager.updatePanelsLayout(subViewsPanels);
    }

    public void addMappings(Set<String> subViewNames, Class<? extends PresentableView> viewClass) {
        int size = subViewNames.size();
        int subViewsSize = subViewsPanels.size();
        if (subViewsSize < size) {
            for (int i = 0; i < size - subViewsSize; i++) {
                subViewsPanels.add(new SubViewPanel());
            }
        }

        Iterator<SubViewPanel> iterator = subViewsPanels.iterator();
        Iterator<String> stringIterator = subViewNames.iterator();
        while (iterator.hasNext()) {
            SubViewPanel subViewPanel = iterator.next();
            if (stringIterator.hasNext()) {
                String name = stringIterator.next();
                subViewPanel.getButton().setText(name);
                subViewPanel.getButton().setVisible(true);
                subViewPanel.setSubViewClass(viewClass);
            } else {
                subViewPanel.getButton().setVisible(false);
            }
        }

        Collections.sort(subViewsPanels, subViewPanelComparator);
    }

    // TODO add Image mapping to button
    public void addMappings(List<Pair<String, Class<? extends PresentableView>>> mappings) {
        int size = mappings.size();
        int subViewsSize = subViewsPanels.size();
        if (subViewsSize < size) {
            for (int i = 0; i < size - subViewsSize; i++) {
                subViewsPanels.add(new SubViewPanel());
            }
        }

        Iterator<SubViewPanel> iterator = subViewsPanels.iterator();
        Iterator<Pair<String, Class<? extends PresentableView>>> pairIterator = mappings.iterator();
        while (iterator.hasNext()) {
            SubViewPanel subViewPanel = iterator.next();
            Pair<String, Class<? extends PresentableView>> pair = pairIterator.next();
            subViewPanel.getButton().setText(pair.getKey());
            subViewPanel.setSubViewClass(pair.getValue());
        }

        Collections.sort(subViewsPanels, subViewPanelComparator);
    }

    public void setSelectedModuleValue(String selectedModuleValue) {
        selectedPanel.setSize(selectedModuleValue.length() * 2, 25);
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
        if (clickedButton.equals(selectedPanel)) {
            return this.getClass();
        }

        for (SubViewPanel subViewsPanel : subViewsPanels) {
            if (subViewsPanel.getButton().equals(clickedButton)) {
                return subViewsPanel.getSubViewClass();
            }
        }

        return null;
    }

    public String getModuleName() {
        return moduleName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return null;
    }

    private static final class SubViewsPanelComparator implements Comparator<SubViewPanel> {

        @Override
        public int compare(SubViewPanel o1, SubViewPanel o2) {
            return o1.getButton().getText().compareTo(o2.getButton().getText());
        }
    }
}
