package sfgamedataeditor.spells.combat;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class ArcheryArtsMagic extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Hit The Target", 0x1c574L));
            add(new Pair<>("Blessing", 0x1c490L));
            add(new Pair<>("Steel Skin", 0x1c5c0L));
            add(new Pair<>("Volley", 0x1c60cL));
            add(new Pair<>("Critical Hits", 0l));
            add(new Pair<>("Shift Life", 0l));
            add(new Pair<>("Riposte", 0l));
        }};
    }
}
