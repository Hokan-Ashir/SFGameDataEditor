package sfgamedataeditor.dataextraction.offsets;

import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.databind.files.FilesContainer;

public class SkillsOffsetHolder extends AbstractOffsetHolder {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setDataLength() {
        setDataLength(0x9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillOffsets() {
        boolean isVersion11 = FilesContainer.INSTANCE.isVersion11();

        if (isVersion11) {
            addOffsetPair(new Pair<>(0x2577bd4, 0x2577ec8));
        } else {
            addOffsetPair(new Pair<>(0x03F85FD4, 0x03F864BF));
        }
    }
}
