package sfgamedataeditor.dataextraction.offsets.buildings;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class BuildingsArmyRequirementsOffsetHolder extends AbstractOffsetHolder {

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
        addOffsetPair(new Pair<>(0x03F81B86, 0x03F81C5C));
    }
}
