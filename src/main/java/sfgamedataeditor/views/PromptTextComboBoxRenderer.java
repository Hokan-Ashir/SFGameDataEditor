package sfgamedataeditor.views;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;

public class PromptTextComboBoxRenderer<T extends String> extends BasicComboBoxRenderer {
    private T prompt;

    public PromptTextComboBoxRenderer(T prompt) {
        this.prompt = prompt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value == null) {
            setText(prompt);
        }

        return this;
    }
}