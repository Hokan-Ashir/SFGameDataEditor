package sfgamedataeditor.spells.blackmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class CursesView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            // SlowFighting = 101
            add(new Pair<>("Aura of Slow Fighting", 94));
            add(new Pair<>("Poison", 5));
            // Inflexibility = 34
            // InflexibilityArea = 38
            add(new Pair<>("Aura of Inflexibility", 95));
            add(new Pair<>("Dispel White Aura", 96));
            // Slowness = 4
            // SlownessArea = 37
            add(new Pair<>("Aura of Slow Walking", 97));
            add(new Pair<>("Dark Banishing", 36));
            // Inability = 100
            add(new Pair<>("Aura of Inability", 98));
            add(new Pair<>("Remediless", 41));
            // DarkMight = 42
            add(new Pair<>("Mutation", 199));
            add(new Pair<>("Area Dark Banishing", 200));
            // MutationChained = 233
            add(new Pair<>("Chain Mutation", 201));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass() {
        return 7;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass() {
        return 3;
    }
}
