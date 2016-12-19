package sfgamedataeditor.views.main.modules.merchants.inventory;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class MerchantInventoryView implements ControllableView {

    private JPanel mainPanel;
    private JList<String> merchantInventoryItemList;
    private JComboBox itemTypeComboBox;
    private JLabel inventoryLabel;
    private JLabel itemTypeLabel;
    private JComboBox itemNameComboBox;
    private JLabel itemNameLabel;
    private JButton goToObjectButton;
    private JScrollPane scrollPane;
    private JPanel listPanel;

    public MerchantInventoryView() {
        merchantInventoryItemList.setModel(new DefaultListModel<String>());
        // http://stackoverflow.com/questions/818163/make-jscrollpane-display-scrollbars-when-jlist-inside-is-changed
        merchantInventoryItemList.setPreferredSize(null);
        inventoryLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items"));
        // TODO temporary; remove later
        itemNameLabel.setVisible(false);
        itemTypeLabel.setVisible(false);
        itemTypeComboBox.setVisible(false);
        itemNameComboBox.setVisible(false);
    }

    public JList<String> getMerchantInventoryItemList() {
        return merchantInventoryItemList;
    }

    public JButton getGoToObjectButton() {
        return goToObjectButton;
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return MerchantInventoryController.class;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
