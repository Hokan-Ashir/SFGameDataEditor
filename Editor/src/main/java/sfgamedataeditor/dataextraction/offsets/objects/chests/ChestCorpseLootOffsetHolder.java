package sfgamedataeditor.dataextraction.offsets.objects.chests;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class ChestCorpseLootOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(11);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x03FA5C53, 0x03FAB489));
    }
}
