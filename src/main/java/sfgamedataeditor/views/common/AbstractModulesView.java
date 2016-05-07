package sfgamedataeditor.views.common;

import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.events.types.ShowViewEvent;

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

    // TODO make all this stuff about setting fired event modules name right and clean
    // cause now, its total bullshit
    public void setModulesComboBoxValue(Object value) {
        ItemListener[] listeners = modulesComboBox.getItemListeners();
        for (ItemListener listener : listeners) {
            modulesComboBox.removeItemListener(listener);
        }

        modulesComboBox.setSelectedItem(value);

        for (ItemListener listener : listeners) {
            modulesComboBox.addItemListener(listener);
        }
    }

    protected void setEventParameter(AbstractMetaEvent metaEvent) {
        for (Event event : metaEvent.getEventList()) {
            if (SetModuleNameEvent.class.isAssignableFrom(event.getClass())) {
                ((SetModuleNameEvent) event).setModuleName(getSelectedModuleValue());
            }
        }
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

    protected void addMapping(String name, AbstractMetaEvent event) {
        comboBoxMapping.put(name, event);
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
    public void clearView() {
        super.clearView();
        modulesComboBox.setSelectedItem(null);
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
