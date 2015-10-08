package views.spells.mindmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class MindMagicView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Mind Essence");
            add("Mind Almightiness");
        }};
    }
}
