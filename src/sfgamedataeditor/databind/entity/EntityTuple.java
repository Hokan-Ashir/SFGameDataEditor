package sfgamedataeditor.databind.entity;

import javax.swing.*;

public class EntityTuple<T extends JComponent> {
    private T component;
    private long offsetInBytes;
    private int lengthInBytes;

    public EntityTuple(T component, long offsetInBytes, int lengthInBytes) {
        this.component = component;
        this.offsetInBytes = offsetInBytes;
        this.lengthInBytes = lengthInBytes;
    }

    public T getComponent() {
        return component;
    }

    public long getOffsetInBytes() {
        return offsetInBytes;
    }

    public int getLengthInBytes() {
        return lengthInBytes;
    }
}
