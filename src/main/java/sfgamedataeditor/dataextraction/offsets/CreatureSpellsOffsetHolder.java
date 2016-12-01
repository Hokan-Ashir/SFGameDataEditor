package sfgamedataeditor.dataextraction.offsets;

import sfgamedataeditor.views.utility.Pair;

public class CreatureSpellsOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(5);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x03F7CF93, 0x03F7DEF1));
    }
}
