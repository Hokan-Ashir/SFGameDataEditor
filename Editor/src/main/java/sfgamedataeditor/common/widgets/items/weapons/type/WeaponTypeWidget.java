package sfgamedataeditor.common.widgets.items.weapons.type;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class WeaponTypeWidget extends AbstractWidget<WeaponTypeWidgetListener> {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox<String> typeComboBox;

    public WeaponTypeWidget() {
        for (Map.Entry<Integer, String> integerStringEntry : WeaponTypesMap.INSTANCE.getTypesMap().entrySet()) {
            typeComboBox.addItem(integerStringEntry.getValue());
        }

        add(getMainPanel());
    }

    JComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    Integer getWeaponTypeByName(String typeName) {
        for (Map.Entry<Integer, String> integerStringEntry : WeaponTypesMap.INSTANCE.getTypesMap().entrySet()) {
            if (integerStringEntry.getValue().equals(typeName)) {
                return integerStringEntry.getKey();
            }
        }

        return 0;
    }

    String getWeaponTypeById(Integer typeId) {
        return WeaponTypesMap.INSTANCE.getTypesMap().get(typeId);
    }

    @Override
    protected void insertListener(WeaponTypeWidgetListener listener) {
        typeComboBox.addItemListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {
        titleLabel.setText(i18nStrings.get(0));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
