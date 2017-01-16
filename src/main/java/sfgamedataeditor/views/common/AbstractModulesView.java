package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.PromptTextComboBoxRenderer;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractModulesView implements PresentableView {

    private final Map<String, Class<? extends PresentableView>> comboBoxMapping = new TreeMap<>();
    private JComboBox<String> modulesComboBox;
    private JPanel mainPanel;

    protected AbstractModulesView(String viewName) {
        modulesComboBox.setRenderer(new PromptTextComboBoxRenderer<>(viewName));
        modulesComboBox.setSelectedIndex(-1);
        modulesComboBox.setToolTipText(viewName);
        initializeComboBox();
    }

    protected Map<String, Class<? extends PresentableView>> getComboBoxMapping() {
        return comboBoxMapping;
    }

    protected JComboBox<String> getModulesComboBox() {
        return modulesComboBox;
    }

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

    public void addMapping(String name, Class<? extends PresentableView> classViewToShow) {
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
