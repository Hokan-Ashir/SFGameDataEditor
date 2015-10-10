package views.spells.combat;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class ArcheryArtsMagic extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Hit The Target");
            add("Blessing");
            add("Steel Skin");
            add("Volley");
            add("Critical Hits");
            add("Shift Life");
            add("Riposte");
        }};
    }
}
