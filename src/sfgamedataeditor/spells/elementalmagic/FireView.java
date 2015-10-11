package sfgamedataeditor.spells.elementalmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class FireView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Fireburst", 0l));
            add(new Pair<>("Fireshield", 0l));
            add(new Pair<>("Fireball", 0l));
            add(new Pair<>("Illuminate", 0l));
            add(new Pair<>("Summon Fire Elemental", 0l));
            add(new Pair<>("Wave of Fire", 0l));
            add(new Pair<>("Melt Resistance", 0l));
            add(new Pair<>("Rain of Fire", 0l));
            add(new Pair<>("Chain Fireburst", 0l));
            add(new Pair<>("Chain Fireball", 0l));
            add(new Pair<>("Summon Fire Golem", 0l));
        }};
    }
}
