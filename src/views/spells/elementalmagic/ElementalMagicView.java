package views.spells.elementalmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class ElementalMagicView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Elemental Essence");
            add("Elemental Almightiness");
        }};
    }
}
