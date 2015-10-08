package views.spells.whitemagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class BoonsView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Aura of Fast Fighting");
            add("Hallow");
            add("Aura of Dexterity");
            add("Dispel Black Aura");
            add("Aura of Fast Waling");
            add("Aura of Light");
            add("Aura of Flexibility");
            add("Invulnerability");
            add("Chain Hallow");
            add("Aura of Eternity");
            add("Fortification");
        }};
    }
}
