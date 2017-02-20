package sfgamedataeditor.dataextraction.offsets.buildings;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class BuildingsOffsetHolder extends AbstractOffsetHolder {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setDataLength() {
        setDataLength(0x17);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x03F81C69, 0x03F82F01));
    }
}
