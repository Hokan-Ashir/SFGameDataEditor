package sfgamedataeditor.dataextraction.offsets.merchants;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class MerchantInventoryOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(4);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x03F864CC, 0x03F867CB));
    }
}
