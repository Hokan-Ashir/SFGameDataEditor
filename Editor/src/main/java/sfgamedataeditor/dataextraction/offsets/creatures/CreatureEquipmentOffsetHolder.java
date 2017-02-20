package sfgamedataeditor.dataextraction.offsets.creatures;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class CreatureEquipmentOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(5);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x03F75C32, 0x03F7CF86));
    }
}
