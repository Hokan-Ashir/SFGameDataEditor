package views.spells.mindmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class OffensiveMagicView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Shock");
            add("Confuse");
            add("Hypnotize");
            add("Amok");
            add("Shockwave");
            add("Disrupt");
            add("Aura of Hypnosis");
            add("Demoralize");
            add("Chain Shock");
            add("Area Hypnosis");
            add("Area Confuse");
        }};
    }
}
