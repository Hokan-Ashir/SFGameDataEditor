package sfgamedataeditor.dataextraction.offsets.creatures;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class CreatureSkillsOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(0x5);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x000613C1, 0x00062E96));
    }
}
