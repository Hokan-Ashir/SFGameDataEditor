package sfgamedataeditor.common.widgets.common.combobox.requirementclass;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;
import java.util.List;

@SuppressWarnings("unused")
public class RequirementClassSubClassWidget extends AbstractWidget<AbstractRequirementClassSubClassWidgetListener> {
    private JPanel mainPanel;
    private JLabel requirementClassLabel;
    private JLabel requirementSubClassLabel;
    private JComboBox<String> requirementClassComboBox;
    private JComboBox<String> requirementSubClassComboBox;

    public RequirementClassSubClassWidget() {
        add(getMainPanel());
    }

    JComboBox<String> getRequirementClassComboBox() {
        return requirementClassComboBox;
    }

    JComboBox<String> getRequirementSubClassComboBox() {
        return requirementSubClassComboBox;
    }

    @Override
    protected void insertListener(AbstractRequirementClassSubClassWidgetListener listener) {
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
