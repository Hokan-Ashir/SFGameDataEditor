package sfgamedataeditor.common.widgets.spells.auratarget;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AuraTargetWidget extends AbstractWidget<AuraTargetWidgetListener> {
    private JPanel mainPanel;
    private JComboBox<String> auraTargetComboBox;
    private JLabel titleLabel;

    private List<Pair<Integer, String>> auraTargetMapping = new ArrayList<Pair<Integer, String>>() {{
        add(new Pair<>(1, I18NService.INSTANCE.getMessage(I18NTypes.SPELLS_GUI, "aura.target.allies")));
        add(new Pair<>(2, I18NService.INSTANCE.getMessage(I18NTypes.SPELLS_GUI, "aura.target.enemies")));
    }};

    public AuraTargetWidget() {
        for (Pair<Integer, String> pair : auraTargetMapping) {
            auraTargetComboBox.addItem(pair.getValue());
        }
        add(getMainPanel());
    }

    public JComboBox<String> getAuraTargetComboBox() {
        return auraTargetComboBox;
    }

    public void setAuraTargetComboBoxValue(int value) {
        for (Pair<Integer, String> pair : auraTargetMapping) {
            if (pair.getKey().equals(value)) {
                auraTargetComboBox.setSelectedItem(pair.getValue());
            }
        }
    }

    public int getAuraTargetComboBoxValue() {
        String selectedItem = (String) auraTargetComboBox.getSelectedItem();
        for (Pair<Integer, String> pair : auraTargetMapping) {
            if (pair.getValue().equals(selectedItem)) {
                return pair.getKey();
            }
        }

        return 0;
    }

    @Override
    protected void insertListener(AuraTargetWidgetListener listener) {
        auraTargetComboBox.addItemListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {
        if (i18nStrings.isEmpty()) {
            return;
        }

        titleLabel.setText(i18nStrings.get(0));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
