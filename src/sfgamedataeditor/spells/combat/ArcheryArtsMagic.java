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
            add(new Pair<>("Hit The Target", 0));
            add(new Pair<>("Blessing", 0));
            add(new Pair<>("Steel Skin", 0));
            add(new Pair<>("Volley", 0));
            add(new Pair<>("Critical Hits", 0));
            add(new Pair<>("Shift Life", 0));
            add(new Pair<>("Riposte", 0));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellClass() {
        return 3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellSubClass() {
        return 0;
    }
}
