package sfgamedataeditor.views.common;

import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.ShowViewEvent;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractModulesView<T extends AbstractView> extends AbstractView<T> implements Containerable {

    private Map<String, List<ShowViewEvent>> comboBoxMapping = new TreeMap<>();
    private JComboBox<String> modulesComboBox;
    private JPanel subModulesPanel;
    private JLabel modulesLabel;
    private JPanel mainPanel;

    public AbstractModulesView(T parentView, String viewName) {
        super(parentView);
        modulesLabel.setText(viewName);
        subModulesPanel.setLayout(new BoxLayout(subModulesPanel, BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initializeComboBox();
        modulesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                Object selectedItem = modulesComboBox.getSelectedItem();
                if (selectedItem == null) {
                    return;
                }

                getChildren().clear();
                List<ShowViewEvent> events = comboBoxMapping.get(selectedItem);
                for (ShowViewEvent event : events) {
                    setEventParameter(event);
                    EventHandlerRegister.INSTANCE.fireEvent(event);
                }
            }
        });
    }

    protected void setEventParameter(ShowViewEvent event) {
        // TODO make abstract
    }

    protected abstract void fillComboBoxMapping();

    private void initializeComboBox() {
        fillComboBoxMapping();
        reinitializeComboBox();
    }

    protected void reinitializeComboBox() {
        modulesComboBox.removeAllItems();
        for (String s : comboBoxMapping.keySet()) {
            modulesComboBox.addItem(s);
        }
    }

    protected void clearComboBoxAndMapping() {
        modulesComboBox.removeAllItems();
        comboBoxMapping.clear();
    }

    protected void selectFirstComboBoxItem() {
        modulesComboBox.setSelectedItem(null);
        modulesComboBox.setSelectedIndex(0);
    }

    protected void addMapping(String name, List<ShowViewEvent> events) {
        comboBoxMapping.put(name, events);
    }

    protected void addMapping(String name, ShowViewEvent event) {
        List<ShowViewEvent> events;
        if (!comboBoxMapping.containsKey(name)) {
            events = new ArrayList<>();
            comboBoxMapping.put(name, events);
        } else {
            events = comboBoxMapping.get(name);
        }

        events.add(event);
    }

    public Object getSelectedModuleValue() {
        return modulesComboBox.getSelectedItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getSubModulesPanel() {
        return subModulesPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearAllComponents() {
        super.clearAllComponents();
        subModulesPanel.removeAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void repaint() {
        super.repaint();
        subModulesPanel.revalidate();
        subModulesPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChildView(AbstractView view) {
        super.addChildView(view);
        subModulesPanel.add(view.getMainPanel());
    }
}
