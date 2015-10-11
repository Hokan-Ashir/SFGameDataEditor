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
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Aura of Slow Fighting", 0l));
            add(new Pair<>("Poison", 0l));
            add(new Pair<>("Aura of Inflexibility", 0l));
            add(new Pair<>("Dispel White Aura", 0l));
            add(new Pair<>("Aura of Slow Walking", 0l));
            add(new Pair<>("Dark Banishing", 0l));
            add(new Pair<>("Aura of Inability", 0l));
            add(new Pair<>("Remediless", 0l));
            add(new Pair<>("Alteration", 0l));
            add(new Pair<>("Area Dark Banishing", 0l));
            add(new Pair<>("Area Alteration", 0l));
        }};
    }
}
