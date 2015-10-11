package sfgamedataeditor.spells.combat;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class HeavyCombatArtsMagic extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Battle Cry", 0l));
            add(new Pair<>("Support", 0l));
            add(new Pair<>("Protect", 0l));
            add(new Pair<>("Steadfastness", 0l));
            add(new Pair<>("Critical Hits", 0l));
            add(new Pair<>("Shift Life", 0l));
            add(new Pair<>("Riposte", 0l));
        }};
    }
}
