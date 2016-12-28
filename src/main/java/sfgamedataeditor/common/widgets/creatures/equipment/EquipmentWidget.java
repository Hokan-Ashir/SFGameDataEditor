package sfgamedataeditor.common.widgets.creatures.equipment;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentWidget extends AbstractWidget<EquipmentWidgetListener> {
    private JPanel mainPanel;
    private JLabel itemTypeLabel;
    private JComboBox<String> itemTypeComboBox;
    private JLabel itemPieceLabel;
    private JComboBox<String> itemPieceComboBox;
    private JButton goToItemButton;

    public EquipmentWidget() {
        itemTypeLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.slot.item.type"));
        itemPieceLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "equipment.slot.item.name"));

        initializeItemTypesComboBox();
        add(getMainPanel());
    }

    // TODO possibly restrict users, so that they can't mindlessly put i.e. rings in chest slot
    private void initializeItemTypesComboBox() {
        List<String> i18nItemTypeKeys = new ArrayList<String>() {{
            add("items.1h.weapon");
            add("items.2h.weapon");
            add("items.bow");
            add("items.armor.helmets");
            add("items.armor.chest.armor");
            add("items.armor.robes");
            add("items.armor.legs.armor");
            add("items.armor.shield");
            add("items.armor.rings");
            add("items.figureNPC");
            add("items.hero.army.units");
        }};

        for (String i18nItemTypeKey : i18nItemTypeKeys) {
            itemTypeComboBox.addItem(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, i18nItemTypeKey));
        }
    }

    public JComboBox<String> getItemTypeComboBox() {
        return itemTypeComboBox;
    }

    public JComboBox<String> getItemPieceComboBox() {
        return itemPieceComboBox;
    }

    @Override
    protected void insertListener(EquipmentWidgetListener listener) {
        itemTypeComboBox.addItemListener(listener);
        itemPieceComboBox.addItemListener(listener);
        goToItemButton.addActionListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {

    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        itemTypeLabel.setVisible(aFlag);
        itemTypeComboBox.setVisible(aFlag);
        itemPieceLabel.setVisible(aFlag);
        itemPieceComboBox.setVisible(aFlag);
    }
}
