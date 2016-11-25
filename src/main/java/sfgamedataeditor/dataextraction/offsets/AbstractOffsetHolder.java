package sfgamedataeditor.dataextraction.offsets;

import sfgamedataeditor.databind.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOffsetHolder {
    private final List<Pair<Integer, Integer>> offsets = new ArrayList<>();
    private int dataLength;

    public AbstractOffsetHolder() {
        setDataLength();
        fillOffsets();
    }

    public List<Pair<Integer, Integer>> getOffsets() {
        return offsets;
    }

    public int getDataLength() {
        return dataLength;
    }

    protected void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    protected void addOffsetPair(Pair<Integer, Integer> offsetPair) {
        offsets.add(offsetPair);
    }

    protected abstract void setDataLength();

    protected abstract void fillOffsets();
}
