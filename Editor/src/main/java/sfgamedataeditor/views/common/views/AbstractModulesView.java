package sfgamedataeditor.views.common.views;

import org.apache.log4j.Logger;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.SubViewPanel;
import sfgamedataeditor.views.common.managers.AbstractModulePanelManager;
import sfgamedataeditor.views.common.managers.DefaultModulesPanelManager;
import sfgamedataeditor.views.utility.Pair;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

import static java.awt.Component.LEFT_ALIGNMENT;

public abstract class AbstractModulesView implements PresentableView {

    private static final Logger LOGGER = Logger.getLogger(AbstractModulesView.class);
    private final JButton selectedPanel = new JButton();
    private Icon selectedIcon;
    private final List<SubViewPanel> subViewsPanels = new ArrayList<>();
    private final Comparator<SubViewPanel> subViewPanelComparator = new SubViewsPanelComparator();
    private AbstractModulePanelManager panelManager;
    private final String moduleName;
    private JPanel selectedRenderPanel;

    protected AbstractModulesView(String viewName) {
        this.moduleName = viewName;
        this.selectedRenderPanel = createSelectedRenderPanel();

        Class<? extends AbstractModulePanelManager> managerClass = getModulesPanelManagerClass();
        try {
            panelManager = managerClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        initializeSubViewsMapping();
    }

    private JPanel createSelectedRenderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(selectedPanel);
        panel.add(Box.createHorizontalStrut(10));

        return panel;
    }

    public abstract void fillSubViewsMappings();

    private void initializeSubViewsMapping() {
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
            JButton button = subViewPanel.getButton();
            if (stringIterator.hasNext()) {
                String name = stringIterator.next();
                setPanelImageAndSize(button, name);

                button.setText(name);
                button.setVisible(true);
                subViewPanel.setSubViewClass(viewClass);
            } else {
                button.setVisible(false);
            }
        }

        Collections.sort(subViewsPanels, subViewPanelComparator);
    }

    protected ImageIcon getPanelImageByPanelName(String panelName) {
        return null;
    }

    protected void addMappings(List<Pair<String, Class<? extends PresentableView>>> mappings) {
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
            String name = pair.getKey();
            JButton button = subViewPanel.getButton();
            button.setText(name);
            setPanelImageAndSize(button, name);
            subViewPanel.setSubViewClass(pair.getValue());
        }

        Collections.sort(subViewsPanels, subViewPanelComparator);
    }

    private void setPanelImageAndSize(JButton button, String name) {
        ImageIcon icon = getPanelImageByPanelName(name);
        button.setIcon(icon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    public void setSelectedModuleValue(String selectedModuleValue) {
        selectedPanel.setSize(selectedModuleValue.length() * 2, selectedPanel.getHeight());
        selectedPanel.setText(selectedModuleValue);
    }

    public String getSelectedModuleName() {
        return selectedPanel.getText();
    }

    public void setSelectedModuleIcon(Icon icon) {
        selectedIcon = icon;
    }

    public Icon getSelectedModuleIcon() {
        return selectedIcon;
    }

    protected Class<? extends AbstractModulePanelManager> getModulesPanelManagerClass() {
        return DefaultModulesPanelManager.class;
    }

    public JButton getSelectedPanelButton() {
        return selectedPanel;
    }

    public JPanel getSelectedPanel() {
        return selectedRenderPanel;
    }

    public JPanel getManagerPanel() {
        return panelManager.getMainPanel();
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
