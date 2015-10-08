package views.spells.blackmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class BlackMagicView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Black Essence");
            add("Black Almightiness");
        }};
    }
}
