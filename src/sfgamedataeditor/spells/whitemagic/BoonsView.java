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
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Aura of Fast Fighting", 0l));
            add(new Pair<>("Hallow", 0l));
            add(new Pair<>("Aura of Dexterity", 0l));
            add(new Pair<>("Dispel Black Aura", 0l));
            add(new Pair<>("Aura of Fast Waling", 0l));
            add(new Pair<>("Aura of Light", 0l));
            add(new Pair<>("Aura of Flexibility", 0l));
            add(new Pair<>("Invulnerability", 0l));
            add(new Pair<>("Chain Hallow", 0l));
            add(new Pair<>("Aura of Eternity", 0l));
            add(new Pair<>("Fortification", 0l));
        }};
    }
}
