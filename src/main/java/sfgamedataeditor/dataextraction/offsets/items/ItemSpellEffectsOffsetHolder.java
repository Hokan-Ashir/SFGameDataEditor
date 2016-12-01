package sfgamedataeditor.dataextraction.offsets.items;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class ItemSpellEffectsOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(5);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x0009A492, 0x0009F347));
    }
}
