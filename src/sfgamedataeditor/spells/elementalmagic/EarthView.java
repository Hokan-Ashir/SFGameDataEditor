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
            add(new Pair<>("Rock Bullet", 139));
            add(new Pair<>("Conservation", 140));
            add(new Pair<>("Detect Metal", 82));
            add(new Pair<>("Decay", 16));
            // Decay Area = 17
            add(new Pair<>("Summon Earth Elemental", 141));
            // RingOfRocks = 78
            // WallOfRocks = 77
            add(new Pair<>("Wave of Rocks", 142));
            // Stone = 87
            add(new Pair<>("Petrify", 25));
            add(new Pair<>("Stone Rain", 76));
            // RockBulletChained = 236
            add(new Pair<>("Chain Rock Bullet", 208));
            add(new Pair<>("Feet Clay", 210));
            add(new Pair<>("Summon Earth Golem", 209));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass() {
        return 5;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass() {
        return 3;
    }
}
