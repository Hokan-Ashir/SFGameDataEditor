package sfgamedataeditor.common.widgets.itemprice;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;
import java.util.List;

public class ItemPriceWidget extends AbstractWidget<ItemPriceWidgetListener> {
    private JPanel mainPanel;
    private JLabel goldAmountLabel;
    private JTextField goldAmountTextField;
    private JLabel silverAmountLabel;
    private JTextField silverAmountTextField;
    private JLabel copperAmountLabel;
    private JTextField copperAmountField;

    @Override
    protected void insertListener(ItemPriceWidgetListener listener) {
        goldAmountTextField.getDocument().addDocumentListener(listener);
        silverAmountTextField.getDocument().addDocumentListener(listener);
        copperAmountField.getDocument().addDocumentListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {

    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
