package sfgamedataeditor.dataextraction.offsets.creatures.production;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class CreatureBuildingsOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(5);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x03F81B86, 0x03F81C5C));
    }
}
