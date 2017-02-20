package sfgamedataeditor.dataextraction.offsets.items;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class ItemPriceParametersOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(22);
    }

    @Override
    protected void fillOffsets() {
        addOffsetPair(new Pair<>(0x0006359E, 0x000897DB));
    }
}
