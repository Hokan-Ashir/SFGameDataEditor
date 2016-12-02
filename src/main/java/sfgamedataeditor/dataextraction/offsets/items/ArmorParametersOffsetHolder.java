package sfgamedataeditor.dataextraction.offsets.items;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class ArmorParametersOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(36);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x000897E8, 0x0008F133));
    }
}
