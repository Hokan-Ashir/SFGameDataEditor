package sfgamedataeditor.common.widgets.combobox.requirementclass;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.datamapping.Mappings;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class RequirementClassSubClassWidget extends AbstractWidget<RequirementClassSubClassWidgetListener> {
    private JPanel mainPanel;
    private JLabel requirementClassLabel;
    private JLabel requirementSubClassLabel;
    private JComboBox<String> requirementClassComboBox;
    private JComboBox<String> requirementSubClassComboBox;

    public RequirementClassSubClassWidget() {
        Map<String, List<String>> map = Mappings.INSTANCE.CLASS_SUBCLASS_COMBOBOX_MAP;
        for (String s : map.keySet()) {
            requirementClassComboBox.addItem(s);
        }

        add(getMainPanel());
    }

    public JComboBox<String> getRequirementClassComboBox() {
        return requirementClassComboBox;
    }

    public JComboBox<String> getRequirementSubClassComboBox() {
        return requirementSubClassComboBox;
    }

    @Override
    protected void insertListener(RequirementClassSubClassWidgetListener listener) {
        requirementClassComboBox.addItemListener(listener);
        requirementSubClassComboBox.addItemListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {
        requirementClassLabel.setText(i18nStrings.get(0));
        requirementSubClassLabel.setText(i18nStrings.get(1));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
