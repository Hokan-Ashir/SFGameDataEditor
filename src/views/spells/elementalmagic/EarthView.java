package views.spells.elementalmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class EarthView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Rock Bullet");
            add("Conservation");
            add("Detect Metal");
            add("Decay");
            add("Summon Earth Elemental");
            add("Wave of Rocks");
            add("Petrify");
            add("Stone Rain");
            add("Chain Rock Bullet");
            add("Clay Feet");
            add("Summon Earth Golem");
        }};
    }
}
