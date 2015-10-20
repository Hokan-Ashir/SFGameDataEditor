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
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Manadrain", 68));
            add(new Pair<>("Sacrifice Mana", 66));
            add(new Pair<>("Detect Magic", 83));
            add(new Pair<>("Manatap", 67));
            // Brilliance = 65
            add(new Pair<>("Aura of Brilliance", 129));
            add(new Pair<>("Enlightenment", 130));
            // ManaTapAura = 172
            add(new Pair<>("Aura of Manatap", 131));
            add(new Pair<>("Meditataion", 132));
            // ManaTapChained = 232
            add(new Pair<>("Chain manatap", 217));
            add(new Pair<>("Manashield", 218));
            add(new Pair<>("Mana Convertion", 219));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass() {
        return 6;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass() {
        return 3;
    }
}
