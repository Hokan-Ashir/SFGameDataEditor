package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.ShowViewDispatcher;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.PromptTextComboBoxRenderer;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractModulesView implements RenderableView {

    private Map<String, Class<? extends RenderableView>> comboBoxMapping = new TreeMap<>();
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

                Class<? extends RenderableView> classViewToShow = comboBoxMapping.get(selectedItem);
                Model model = createModel();
                ShowViewDispatcher.INSTANCE.showView(classViewToShow, model);
            }
        });
    }

    protected abstract Model createModel();

    public void setModulesComboBoxValue(final Object value) {
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(modulesComboBox) {
            @Override
            protected void setValues() {
                modulesComboBox.setSelectedItem(value);
            }
        });
    }

    public Map<String, Class<? extends RenderableView>> getComboBoxMapping() {
        return comboBoxMapping;
    }

    public JComboBox<String> getModulesComboBox() {
        return modulesComboBox;
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

    public void reinitializeComboBox() {
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

    public void addMapping(String name, Class<? extends RenderableView> classViewToShow) {
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
