package sfgamedataeditor.spells.elementalmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class FireView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Fireburst", 1));
            add(new Pair<>("Fireshield", 0));
            add(new Pair<>("Fireball", 0));
            add(new Pair<>("Illuminate", 0));
            add(new Pair<>("Summon Fire Elemental", 0));
            add(new Pair<>("Wave of Fire", 0));
            add(new Pair<>("Melt Resistance", 0));
            add(new Pair<>("Rain of Fire", 0));
            add(new Pair<>("Chain Fireburst", 0));
            add(new Pair<>("Chain Fireball", 0));
            add(new Pair<>("Summon Fire Golem", 0));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellClass() {
        return 5;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellSubClass() {
        return 1;
    }
}
