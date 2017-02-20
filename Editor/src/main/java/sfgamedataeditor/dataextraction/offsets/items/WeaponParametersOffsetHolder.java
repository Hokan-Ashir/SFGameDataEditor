package sfgamedataeditor.dataextraction.offsets.items;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class WeaponParametersOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(16);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x00091734, 0x00094443));
    }
}
