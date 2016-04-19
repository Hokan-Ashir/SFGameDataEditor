package sfgamedataeditor.dataextraction.offsets;

import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.databind.files.FilesContainer;

public class SpellsOffsetHolder extends AbstractOffsetHolder {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setDataLength() {
        setDataLength(0x4c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillOffsets() {
        boolean isVersion11 = FilesContainer.INSTANCE.isVersion11();

        if (isVersion11) {
            // TODO find appropriate offset (instead of 0x3fd13)
            addOffsetPair(new Pair<>(0x20, 0x3fd13));
        } else {
            addOffsetPair(new Pair<>(0x20, 0xf6d3));
            addOffsetPair(new Pair<>(0x1105c, 0x3fd13));
        }
    }
}
