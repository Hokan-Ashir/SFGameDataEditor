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
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Rock Bullet", 0l));
            add(new Pair<>("Conservation", 0l));
            add(new Pair<>("Detect Metal", 0l));
            add(new Pair<>("Decay", 0l));
            add(new Pair<>("Summon Earth Elemental", 0l));
            add(new Pair<>("Wave of Rocks", 0l));
            add(new Pair<>("Petrify", 0l));
            add(new Pair<>("Stone Rain", 0l));
            add(new Pair<>("Chain Rock Bullet", 0l));
            add(new Pair<>("Clay Feet", 0l));
            add(new Pair<>("Summon Earth Golem", 0l));
        }};
    }
}
