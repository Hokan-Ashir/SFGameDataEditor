package sfgamedataeditor.spells.elementalmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class EarthView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Rock Bullet", 0));
            add(new Pair<>("Conservation", 0));
            add(new Pair<>("Detect Metal", 0));
            add(new Pair<>("Decay", 0));
            add(new Pair<>("Summon Earth Elemental", 0));
            add(new Pair<>("Wave of Rocks", 0));
            add(new Pair<>("Petrify", 0));
            add(new Pair<>("Stone Rain", 0));
            add(new Pair<>("Chain Rock Bullet", 0));
            add(new Pair<>("Clay Feet", 0));
            add(new Pair<>("Summon Earth Golem", 0));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellClass() {
        return 5;
    }

    @Override
    protected int getSpellSubClass() {
        return 3;
    }
}
