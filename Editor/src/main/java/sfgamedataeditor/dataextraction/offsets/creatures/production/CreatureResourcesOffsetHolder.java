package sfgamedataeditor.dataextraction.offsets.creatures.production;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class CreatureResourcesOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(4);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x03F7DEFE, 0x03F7E0FD));
    }
}
