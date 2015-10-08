package views.spells.whitemagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class WhiteMagicView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("White Essence");
            add("White Amlightiness");
        }};
    }
}
