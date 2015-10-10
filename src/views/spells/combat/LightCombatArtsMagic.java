package views.spells.combat;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class LightCombatArtsMagic extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Berserk");
            add("Blessing");
            add("Sanctuary");
            add("Endurance");
            add("Critical Hits");
            add("Shift Life");
            add("Riposte");
        }};
    }
}
