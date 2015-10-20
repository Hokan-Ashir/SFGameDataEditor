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
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            // Arrow = 84
            add(new Pair<>("True Shot", 156));
            add(new Pair<>("Blessing", 153));
            add(new Pair<>("Steel Skin", 157));
            add(new Pair<>("Salvo", 158));
            add(new Pair<>("Critical Hits", 222));
            add(new Pair<>("Shift Life", 220));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass() {
        return 3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass() {
        return 0;
    }
}
