package sfgamedataeditor.views.common;

import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.views.PromptTextComboBoxRenderer;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractModulesView<T extends AbstractView, E extends AbstractMetaEvent> extends AbstractView<T> implements Processable<E> {

    private Map<String, AbstractMetaEvent> comboBoxMapping = new TreeMap<>();
    private JComboBox<String> modulesComboBox;
    private JPanel mainPanel;

    public AbstractModulesView(T parentView, String viewName) {
        super(parentView);
        modulesComboBox.setRenderer(new PromptTextComboBoxRenderer(viewName));
        modulesComboBox.setSelectedIndex(-1);
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
    public void setModulesComboBoxValue(final Object value) {
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(modulesComboBox) {
            @Override
            protected void setValues() {
                modulesComboBox.setSelectedItem(value);
            }
        });
    }

    protected void setEventParameter(AbstractMetaEvent metaEvent) {
        for (Event event : metaEvent.getEventList()) {
            if (SetModuleNameEvent.class.isAssignableFrom(event.getClass())) {
                event.setObjectParameter(getSelectedModuleValue());
            }
        }
    }

    protected abstract void fillComboBoxMapping();

    private void initializeComboBox() {
        fillComboBoxMapping();
        reinitializeComboBox();
    }

    protected void reinitializeComboBox() {
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(modulesComboBox) {
            @Override
            protected void setValues() {
                modulesComboBox.removeAllItems();
                for (String s : comboBoxMapping.keySet()) {
                    modulesComboBox.addItem(s);
                }
            }
        });
    }

    protected void clearComboBoxAndMapping() {
        modulesComboBox.removeAllItems();
        comboBoxMapping.clear();
    }

    protected void addMapping(String name, AbstractMetaEvent event) {
        comboBoxMapping.put(name, event);
    }

    public String getSelectedModuleValue() {
        return (String) modulesComboBox.getSelectedItem();
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
}
