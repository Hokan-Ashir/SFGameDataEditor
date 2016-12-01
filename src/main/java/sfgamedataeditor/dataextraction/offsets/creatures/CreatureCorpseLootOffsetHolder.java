package sfgamedataeditor.dataextraction.offsets.creatures;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class CreatureCorpseLootOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(11);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x03F7E10A, 0x03F81B79));
    }
}
