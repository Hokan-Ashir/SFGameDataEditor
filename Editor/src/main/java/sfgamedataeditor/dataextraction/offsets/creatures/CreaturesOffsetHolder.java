package sfgamedataeditor.dataextraction.offsets.creatures;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class CreaturesOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(47);
    }

    @Override
    protected void fillOffsets() {
        // unit parameters
        addOffsetPair(new Pair<>(0x0004421D, 0x000613B4));
    }
}
