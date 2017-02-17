package sfgamedataeditor.dataextraction.offsets.buildings;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class BuildingsRequirementsOffsetHolder extends AbstractOffsetHolder {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setDataLength() {
        setDataLength(0x5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x03F85BBE, 0x03F85EFB));
    }
}
