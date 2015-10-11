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
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Thorn Shield", 0l));
            add(new Pair<>("Cure Disease", 0l));
            add(new Pair<>("Summon Wolf", 0l));
            add(new Pair<>("Aura of Regeneration", 0l));
            add(new Pair<>("Charm Animal", 0l));
            add(new Pair<>("Cure Poison", 0l));
            add(new Pair<>("Summon Bear", 0l));
            add(new Pair<>("Dominate Animal", 0l));
            add(new Pair<>("Summon Tree Wrath", 0l));
            add(new Pair<>("Roots", 0l));
            add(new Pair<>("Area Roots", 0l));
        }};
    }
}
