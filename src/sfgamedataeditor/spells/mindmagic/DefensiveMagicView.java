package sfgamedataeditor.spells.mindmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class DefensiveMagicView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Manadrain", 0l));
            add(new Pair<>("Sacrifice Mana", 0l));
            add(new Pair<>("Detect Magic", 0l));
            add(new Pair<>("Manatap", 0l));
            add(new Pair<>("Aura of Brilliance", 0l));
            add(new Pair<>("Enlightenment", 0l));
            add(new Pair<>("Aura of Manatap", 0l));
            add(new Pair<>("Meditataion", 0l));
            add(new Pair<>("Chain manatap", 0l));
            add(new Pair<>("Manashield", 0l));
            add(new Pair<>("Mana Convertion", 0l));
        }};
    }
}
