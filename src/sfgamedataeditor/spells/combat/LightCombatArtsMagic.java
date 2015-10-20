package sfgamedataeditor.spells.combat;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class LightCombatArtsMagic extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Berserk", 0));
            add(new Pair<>("Blessing", 0));
            add(new Pair<>("Sanctuary", 0));
            add(new Pair<>("Endurance", 0));
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
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellSubClass() {
        return 0;
    }
}
