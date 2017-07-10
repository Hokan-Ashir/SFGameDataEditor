package sfgamedataeditor.views.common.views;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.SubViewPanel;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.managers.AbstractModulePanelManager;
import sfgamedataeditor.views.common.managers.DefaultModulesPanelManager;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static java.awt.Component.LEFT_ALIGNMENT;

public abstract class AbstractModulesView implements PresentableView {

    private static final Logger LOGGER = Logger.getLogger(AbstractModulesView.class);
    private final SubViewPanel selectedPanel = new SubViewPanel();
    private final List<SubViewPanel> subViewsPanels = new ArrayList<>();
    private final Comparator<SubViewPanel> subViewPanelComparator = new SubViewsPanelComparator();
    private AbstractModulePanelManager panelManager;
    private String moduleName;
    private final JPanel selectedRenderPanel;

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

        refillSubViewsMapping();
    }

    private JPanel createSelectedRenderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(selectedPanel.getButton());
        panel.add(Box.createHorizontalStrut(10));

        return panel;
    }

    public abstract void fillSubViewsMappings();

    protected void refillSubViewsMapping() {
        subViewsPanels.clear();
        fillSubViewsMappings();
    }

    public void updateSubViewsLayout() {
        panelManager.updatePanelsLayout(subViewsPanels);
    }

    public void addMappings(List<ObjectTuple> mappings, Class<? extends PresentableView> viewClass) {
        int size = mappings.size();
        int subViewsSize = subViewsPanels.size();
        if (subViewsSize < size) {
            for (int i = 0; i < size - subViewsSize; i++) {
                subViewsPanels.add(new SubViewPanel());
            }
        }

        Iterator<SubViewPanel> iterator = subViewsPanels.iterator();
        Iterator<ObjectTuple> stringIterator = mappings.iterator();
        while (iterator.hasNext()) {
            SubViewPanel subViewPanel = iterator.next();
            JButton button = subViewPanel.getButton();
            if (stringIterator.hasNext()) {
                ObjectTuple tuple = stringIterator.next();
                String name = tuple.getName();
                setPanelImageAndSize(button, tuple.getObjectId());

                button.setText(name);
                button.setVisible(true);
                subViewPanel.setSubViewClass(viewClass);
                subViewPanel.setObjectId(tuple.getObjectId());
            } else {
                button.setVisible(false);
            }
        }

        Collections.sort(subViewsPanels, subViewPanelComparator);
    }

    protected String getIconPath() {
        return "";
    }

    protected void addMappings(List<SubViewPanelTuple> mappings) {
        int size = mappings.size();
        int subViewsSize = subViewsPanels.size();
        if (subViewsSize < size) {
            for (int i = 0; i < size - subViewsSize; i++) {
                subViewsPanels.add(new SubViewPanel());
            }
        }

        Iterator<SubViewPanel> iterator = subViewsPanels.iterator();
        Iterator<SubViewPanelTuple> tupleIterator = mappings.iterator();
        while (iterator.hasNext()) {
            SubViewPanel subViewPanel = iterator.next();
            SubViewPanelTuple tuple = tupleIterator.next();
            String name = tuple.getName();
            JButton button = subViewPanel.getButton();
            button.setText(name);
            setPanelImageAndSize(button, tuple.getObjectId());
            subViewPanel.setSubViewClass(tuple.getPresentableViewClass());
            subViewPanel.setObjectId(tuple.getObjectId());
        }

        Collections.sort(subViewsPanels, subViewPanelComparator);
    }

    private void setPanelImageAndSize(JButton button, Integer objectId) {
        ImageIcon icon = ImageIconsCache.INSTANCE.getImageIcon(getIconPath(), objectId);
        button.setIcon(icon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    public void setSelectedModuleValue(String selectedModuleValue) {
        selectedPanel.getButton().setSize(selectedModuleValue.length() * 2, selectedPanel.getButton().getHeight());
        selectedPanel.getButton().setText(selectedModuleValue);
    }

    public String getSelectedModuleName() {
        return selectedPanel.getButton().getText();
    }

    public Integer getSelectedModuleObjectId() {
        return selectedPanel.getObjectId();
    }

    public void setSelectedObjectId(Integer objectId) {
        selectedPanel.setObjectId(objectId);
    }

    protected Class<? extends AbstractModulePanelManager> getModulesPanelManagerClass() {
        return DefaultModulesPanelManager.class;
    }

    public JButton getSelectedPanelButton() {
        return selectedPanel.getButton();
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
        if (clickedButton.equals(selectedPanel.getButton())) {
            return this.getClass();
        }

        for (SubViewPanel subViewsPanel : subViewsPanels) {
            if (subViewsPanel.getButton().equals(clickedButton)) {
                return subViewsPanel.getSubViewClass();
            }
        }

        return null;
    }

    public Integer getSubPanelViewObjectId(JButton clickedButton) {
        if (clickedButton.equals(selectedPanel.getButton())) {
            return selectedPanel.getObjectId();
        }

        for (SubViewPanel subViewsPanel : subViewsPanels) {
            if (subViewsPanel.getButton().equals(clickedButton)) {
                return subViewsPanel.getObjectId();
            }
        }

        return null;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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
