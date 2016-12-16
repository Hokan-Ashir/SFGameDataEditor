package sfgamedataeditor.dataextraction.offsets.merchants;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class MerchantInventoryItemsOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(6);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x03F867D8, 0x03F8E7AB));
    }
}
