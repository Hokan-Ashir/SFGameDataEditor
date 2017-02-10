package sfgamedataeditor.common.widgets.items.itemprice;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;
import javax.swing.text.Document;
import java.util.List;

public class ItemPriceWidget extends AbstractWidget<ItemPriceWidgetListener> {
    private JPanel mainPanel;
    private JLabel goldAmountLabel;
    private JTextField goldAmountTextField;
    private JLabel silverAmountLabel;
    private JTextField silverAmountTextField;
    private JLabel copperAmountLabel;
    private JTextField copperAmountField;
    private JLabel titleLabel;
    private JSeparator separator;

    public ItemPriceWidget() {
        add(getMainPanel());
    }

    public Integer getGoldAmount() {
        return getFieldValue(goldAmountTextField);
    }

    public Integer getSilverAmount() {
        return getFieldValue(silverAmountTextField);
    }

    public Integer getCopperAmount() {
        return getFieldValue(copperAmountField);
    }

    private Integer getFieldValue(JTextField field) {
        if (field.getText().isEmpty()) {
            return 0;
        }

        return Integer.valueOf(field.getText());
    }

    public void setGoldAmount(int goldAmount) {
        setFieldValue(goldAmountTextField, goldAmount);
    }

    public void setSilverAmount(int silverAmount) {
        setFieldValue(silverAmountTextField, silverAmount);
    }

    public void setCopperAmount(int copperAmount) {
        setFieldValue(copperAmountField, copperAmount);
    }

    private void setFieldValue(JTextField field, int value) {
        field.setText(String.valueOf(value));
    }

    public boolean isGoldAmountField(Document document) {
        return goldAmountTextField.getDocument().equals(document);
    }

    public Integer getFieldValue(Document document) {
        if (goldAmountTextField.getDocument().equals(document)) {
            return Integer.valueOf(goldAmountTextField.getText());
        } else if (silverAmountTextField.getDocument().equals(document)) {
            return Integer.valueOf(silverAmountTextField.getText());
        } else {
            return Integer.valueOf(copperAmountField.getText());
        }
    }

    public boolean isFieldOfDocumentIsEmpty(Document document) {
        if (goldAmountTextField.getDocument().equals(document)) {
            return goldAmountTextField.getText().isEmpty();
        } else if (silverAmountTextField.getDocument().equals(document)) {
            return silverAmountTextField.getText().isEmpty();
        } else {
            return copperAmountField.getText().isEmpty();
        }
    }

    @Override
    protected void insertListener(ItemPriceWidgetListener listener) {
        goldAmountTextField.getDocument().addDocumentListener(listener);
        silverAmountTextField.getDocument().addDocumentListener(listener);
        copperAmountField.getDocument().addDocumentListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {
        titleLabel.setText(i18nStrings.get(0));
        goldAmountLabel.setText(i18nStrings.get(1));
        silverAmountLabel.setText(i18nStrings.get(2));
        copperAmountLabel.setText(i18nStrings.get(3));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
