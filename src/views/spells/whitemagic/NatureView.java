package views.spells.whitemagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class NatureView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Thorn Shield");
            add("Cure Disease");
            add("Summon Wolf");
            add("Aura of Regeneration");
            add("Charm Animal");
            add("Cure Poison");
            add("Summon Bear");
            add("Dominate Animal");
            add("Summon Tree Wrath");
            add("Roots");
            add("Area Roots");
        }};
    }
}
