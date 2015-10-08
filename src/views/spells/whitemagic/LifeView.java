package views.spells.whitemagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class LifeView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Healing");
            add("Area Healing");
            add("Aura of Strength");
            add("Greater Healing");
            add("Aura of Healing");
            add("Aura of Endurance");
            add("Sentiel Healing");
            add("Suicide Healing");
            add("Help");
            add("Vengeance");
            add("Healing Touch");
        }};
    }
}
