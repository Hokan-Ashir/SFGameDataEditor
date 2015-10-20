package sfgamedataeditor.spells.whitemagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class LifeView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Healing", 0));
            add(new Pair<>("Area Healing", 0));
            add(new Pair<>("Aura of Strength", 0));
            add(new Pair<>("Greater Healing", 0));
            add(new Pair<>("Aura of Healing", 0));
            add(new Pair<>("Aura of Endurance", 0));
            add(new Pair<>("Sentiel Healing", 0));
            add(new Pair<>("Suicide Healing", 0));
            add(new Pair<>("Help", 0));
            add(new Pair<>("Vengeance", 0));
            add(new Pair<>("Healing Touch", 0));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellClass() {
        return 4;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellSubClass() {
        return 1;
    }
}
