package views.spells.elementalmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class IceView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Iceburst");
            add("Iceshield");
            add("Freeze");
            add("Fog");
            add("Summon Ice Elemental");
            add("Wave of Ice");
            add("Chill Resistance");
            add("Blizzard");
            add("Chain Iceburst");
            add("Area Freeze");
            add("Summon Ice Golem");
        }};
    }
}
