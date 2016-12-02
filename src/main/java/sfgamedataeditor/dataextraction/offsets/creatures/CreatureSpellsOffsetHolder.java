package sfgamedataeditor.dataextraction.offsets.creatures;

import sfgamedataeditor.dataextraction.offsets.AbstractOffsetHolder;
import sfgamedataeditor.views.utility.Pair;

public class CreatureSpellsOffsetHolder extends AbstractOffsetHolder {
    @Override
    protected void setDataLength() {
        setDataLength(5);
    }

    @Override
    protected void fillOffsets() {
        // hero spells (http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=258&sid=c36d0fbf6779c51b97a2e021ad97cc44)
        addOffsetPair(new Pair<>(0x00062EA3, 0x00063591));
        // unit spells (http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=281&sid=c36d0fbf6779c51b97a2e021ad97cc44)
        addOffsetPair(new Pair<>(0x03F7CF93, 0x03F7DEF1));
    }
}
