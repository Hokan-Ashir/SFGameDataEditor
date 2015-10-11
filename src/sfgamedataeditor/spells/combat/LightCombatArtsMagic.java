package sfgamedataeditor.spells.combat;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class LightCombatArtsMagic extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Berserk", 0l));
            add(new Pair<>("Blessing", 0l));
            add(new Pair<>("Sanctuary", 0l));
            add(new Pair<>("Endurance", 0l));
            add(new Pair<>("Critical Hits", 0l));
            add(new Pair<>("Shift Life", 0l));
            add(new Pair<>("Riposte", 0l));
        }};
    }
}
