package views.spells.mindmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class DefensiveMagicView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Manadrain");
            add("Sacrifice Mana");
            add("Detect Magic");
            add("Manatap");
            add("Aura of Brilliance");
            add("Enlightenment");
            add("Aura of Manatap");
            add("Meditataion");
            add("Chain manatap");
            add("Manashield");
            add("Mana Convertion");
        }};
    }
}
