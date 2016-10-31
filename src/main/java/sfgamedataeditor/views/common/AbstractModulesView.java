package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.ShowViewDispatcher;
import sfgamedataeditor.views.PromptTextComboBoxRenderer;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractModulesView extends AbstractView {

    private Map<String, Class<? extends AbstractView>> comboBoxMapping = new TreeMap<>();
    private JComboBox<String> modulesComboBox;
    private JPanel mainPanel;

    public AbstractModulesView(String viewName) {
        modulesComboBox.setRenderer(new PromptTextComboBoxRenderer<>(viewName));
        modulesComboBox.setSelectedIndex(-1);
        modulesComboBox.setToolTipText(viewName);
        initializeComboBox();
        modulesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                String selectedItem = (String) modulesComboBox.getSelectedItem();
                if (selectedItem == null) {
                    return;
                }

                Class<? extends AbstractView> classViewToShow = comboBoxMapping.get(selectedItem);
//                setEventParameter(metaEvent);
                ShowViewDispatcher.INSTANCE.showView(classViewToShow, null);
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

//    protected void setEventParameter(AbstractMetaEvent metaEvent) {
//        for (Event event : metaEvent.getEventList()) {
//            if (SetModuleNameEvent.class.isAssignableFrom(event.getClass())) {
//                event.setObjectParameter(getSelectedModuleValue());
//            }
//        }
//    }

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

    protected void addMapping(String name, Class<? extends AbstractView> classViewToShow) {
        comboBoxMapping.put(name, classViewToShow);
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
}
