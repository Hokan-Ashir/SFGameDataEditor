package sfgamedataeditor.views.common;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.EventHandlerRegister;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractModulesView<T extends AbstractView> extends AbstractView<T> {

    private Map<String, AbstractMetaEvent> comboBoxMapping = new TreeMap<>();
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
                AbstractMetaEvent metaEvent = comboBoxMapping.get(selectedItem);
                setEventParameter(metaEvent);
                EventHandlerRegister.INSTANCE.fireEvent(metaEvent);
            }
        });
    }

    protected void setEventParameter(AbstractMetaEvent event) {
        // TODO make abstract
    }

    protected abstract void fillComboBoxMapping();

    private void initializeComboBox() {
        fillComboBoxMapping();
        reinitializeComboBox();
    }

    protected void reinitializeComboBox() {
        // to not trigger item change events (and so event firing) cancel
        // listening to item change events for time of
        // combobox population
        ItemListener[] listeners = modulesComboBox.getItemListeners();
        for (ItemListener listener : listeners) {
            modulesComboBox.removeItemListener(listener);
        }

        modulesComboBox.removeAllItems();
        for (String s : comboBoxMapping.keySet()) {
            modulesComboBox.addItem(s);
        }

        for (ItemListener listener : listeners) {
            modulesComboBox.addItemListener(listener);
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

    protected void addMapping(String name, AbstractMetaEvent events) {
        comboBoxMapping.put(name, events);
    }

    public Object getSelectedModuleValue() {
        return modulesComboBox.getSelectedItem();
    }

    /**
     * {@inheritDoc}
     */
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
