package sfgamedataeditor.common.widgets.items.weapons.metrial;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponMaterialWidget extends AbstractWidget<WeaponMaterialWidgetListener> {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox<String> materialComboBox;

    //    01 00 - default weapon material
    //    02 00 - Weapon Material Fist
    //    03 00 - Weapon Material Wood
    //    04 00 - Weapon Material Stone
    //    05 00 - Weapon Material Metal
    //    06 00 - Weapon Material Magic Metal
    //    07 00 - Weapon Material Bone
    private static final Map<Integer, String> materialMap = new HashMap<Integer, String>() {{
       put(1, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "material.default"));
       put(2, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "material.fist"));
       put(3, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "material.wood"));
       put(4, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "material.stone"));
       put(5, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "material.metal"));
       put(6, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "material.magic.metal"));
       put(7, I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, "material.bone"));
    }};

    public WeaponMaterialWidget() {
        for (Map.Entry<Integer, String> integerStringEntry : materialMap.entrySet()) {
            materialComboBox.addItem(integerStringEntry.getValue());
        }

        add(getMainPanel());
    }

    @Override
    protected void insertListener(WeaponMaterialWidgetListener listener) {
        materialComboBox.addItemListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {
        titleLabel.setText(i18nStrings.get(0));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Integer getWeaponMaterialByName(String materialName) {
        for (Map.Entry<Integer, String> integerStringEntry : materialMap.entrySet()) {
            if (integerStringEntry.getValue().equals(materialName)) {
                return integerStringEntry.getKey();
            }
        }

        return 0;
    }

    public String getWeaponMaterialById(Integer materialId) {
        return materialMap.get(materialId);
    }

    public JComboBox<String> getMaterialComboBox() {
        return materialComboBox;
    }
}
