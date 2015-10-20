package sfgamedataeditor.spells.whitemagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class BoonsView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Aura of Fast Fighting", 0));
            add(new Pair<>("Hallow", 0));
            add(new Pair<>("Aura of Dexterity", 0));
            add(new Pair<>("Dispel Black Aura", 0));
            add(new Pair<>("Aura of Fast Waling", 0));
            add(new Pair<>("Aura of Light", 0));
            add(new Pair<>("Aura of Flexibility", 0));
            add(new Pair<>("Invulnerability", 0));
            add(new Pair<>("Chain Hallow", 0));
            add(new Pair<>("Aura of Eternity", 0));
            add(new Pair<>("Fortification", 0));
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
        return 3;
    }
}
