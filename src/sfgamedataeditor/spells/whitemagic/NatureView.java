package sfgamedataeditor.spells.whitemagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class NatureView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Thorn Shield", 0));
            add(new Pair<>("Cure Disease", 0));
            add(new Pair<>("Summon Wolf", 0));
            add(new Pair<>("Aura of Regeneration", 0));
            add(new Pair<>("Charm Animal", 0));
            add(new Pair<>("Cure Poison", 0));
            add(new Pair<>("Summon Bear", 0));
            add(new Pair<>("Dominate Animal", 0));
            add(new Pair<>("Summon Tree Wrath", 0));
            add(new Pair<>("Roots", 0));
            add(new Pair<>("Area Roots", 0));
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
        return 2;
    }
}
