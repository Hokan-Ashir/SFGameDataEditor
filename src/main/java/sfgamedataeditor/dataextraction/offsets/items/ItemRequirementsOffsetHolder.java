package sfgamedataeditor.dataextraction.offsets.items;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class ItemRequirementsOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(6);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x00094450, 0x0009A485));
    }
}
