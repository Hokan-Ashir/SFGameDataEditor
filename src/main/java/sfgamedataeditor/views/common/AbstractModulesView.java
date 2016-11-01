package sfgamedataeditor.views.common;

import sfgamedataeditor.views.PromptTextComboBoxRenderer;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractModulesView implements ControllableView {

    private Map<String, Class<? extends ControllableView>> comboBoxMapping = new TreeMap<>();
    private JComboBox<String> modulesComboBox;
    private JPanel mainPanel;

    public AbstractModulesView(String viewName) {
        modulesComboBox.setRenderer(new PromptTextComboBoxRenderer<>(viewName));
        modulesComboBox.setSelectedIndex(-1);
        modulesComboBox.setToolTipText(viewName);
        initializeComboBox();
    }

    public Map<String, Class<? extends ControllableView>> getComboBoxMapping() {
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

    public void addMapping(String name, Class<? extends ControllableView> classViewToShow) {
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
