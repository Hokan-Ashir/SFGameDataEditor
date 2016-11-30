package sfgamedataeditor.dataextraction.offsets;

import sfgamedataeditor.views.utility.Pair;

public class CreaturesCommonsOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(64);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x03F4CDE6, 0x03F75C25));
    }
}
