package sfgamedataeditor.spells.combat;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class HeavyCombatArtsMagic extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Battle Cry", 0));
            add(new Pair<>("Support", 0));
            add(new Pair<>("Protect", 0));
            add(new Pair<>("Steadfastness", 0));
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
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellSubClass() {
        return 0;
    }
}
