package sfgamedataeditor.dataextraction.offsets.items;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class ItemEffectsOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(4);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x0012B373, 0x0012D16A));
    }
}
