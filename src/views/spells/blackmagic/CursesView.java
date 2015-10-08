package views.spells.blackmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class CursesView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Aura of Slow Fighting");
            add("Poison");
            add("Aura of Inflexibility");
            add("Dispel White Aura");
            add("Aura of Slow Walking");
            add("Dark Banishing");
            add("Aura of Inability");
            add("Remediless");
            add("Alteration");
            add("Area Dark Banishing");
            add("Area Alteration");
        }};
    }
}
