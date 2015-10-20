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
            add(new Pair<>("Aura of Slow Fighting", 0));
            add(new Pair<>("Poison", 0));
            add(new Pair<>("Aura of Inflexibility", 0));
            add(new Pair<>("Dispel White Aura", 0));
            add(new Pair<>("Aura of Slow Walking", 0));
            add(new Pair<>("Dark Banishing", 0));
            add(new Pair<>("Aura of Inability", 0));
            add(new Pair<>("Remediless", 0));
            add(new Pair<>("Alteration", 0));
            add(new Pair<>("Area Dark Banishing", 0));
            add(new Pair<>("Area Alteration", 0));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellClass() {
        return 7;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellSubClass() {
        return 3;
    }
}
