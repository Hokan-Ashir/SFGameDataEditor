package views.spells.combat;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class HeavyCombatArtsMagic extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Battle Cry");
            add("Support");
            add("Protect");
            add("Steadfastness");
            add("Critical Hits");
            add("Shift Life");
            add("Riposte");
        }};
    }
}
