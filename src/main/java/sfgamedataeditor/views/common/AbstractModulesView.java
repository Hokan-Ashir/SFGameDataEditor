package sfgamedataeditor.views.common;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractModulesView<T extends AbstractView> extends AbstractView<T> implements Containerable {

    private Map<String, Class<? extends AbstractView>> comboBoxMapping = new TreeMap<>();
    private JComboBox<String> modulesComboBox;
    private JPanel subModulesPanel;
    private JLabel modulesLabel;
    private JPanel mainPanel;

    public AbstractModulesView(T parentView) {
        super(parentView);
        subModulesPanel.setLayout(new BoxLayout(subModulesPanel, BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initializeComboBox();
        modulesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object selectedItem = modulesComboBox.getSelectedItem();
                if (selectedItem == null) {
                    return;
                }

                Class<? extends AbstractView> viewClass = comboBoxMapping.get(selectedItem);
                AbstractView view = ViewRegister.INSTANCE.getView(viewClass, AbstractModulesView.this);
                view.show();
            }
        });
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

    public Map<String, Class<? extends AbstractView>> getComboBoxMapping() {
        return comboBoxMapping;
    }

    public Object getSelectedModuleValue() {
        return modulesComboBox.getSelectedItem();
    }

    public void setModulesLabelText(String text) {
        modulesLabel.setText(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getSubModulesPanel() {
        return subModulesPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
