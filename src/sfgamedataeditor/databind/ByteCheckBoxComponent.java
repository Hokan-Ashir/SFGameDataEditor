package sfgamedataeditor.databind;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ByteCheckBoxComponent extends JComponent {
    private static final int BYTE_BIT_LENGTH = 8;

    private List<Pair<JLabel, JCheckBox>> componentList = new ArrayList<>(BYTE_BIT_LENGTH);

    public ByteCheckBoxComponent(List<String> byteValueNames) {
        for (int i = 0; i < BYTE_BIT_LENGTH; i++) {
            JLabel label = new JLabel(byteValueNames.get(i));
            JCheckBox checkBox = new JCheckBox();
            Pair<JLabel, JCheckBox> pair = new Pair<>(label, checkBox);
            componentList.add(pair);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(Graphics g) {
        for (Pair<JLabel, JCheckBox> pair : componentList) {
            pair.getKey().paint(g);
            pair.getValue().paint(g);
        }
    }
}
