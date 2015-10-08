package views.spells.elementalmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class FireView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Fireburst");
            add("Fireshield");
            add("Fireball");
            add("Illuminate");
            add("Summon Fire Elemental");
            add("Wave of Fire");
            add("Melt Resistance");
            add("Rain of Fire");
            add("Chain Fireburst");
            add("Chain Fireball");
            add("Summon Fire Golem");
        }};
    }
}
