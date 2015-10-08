package views.spells.mindmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Self Illusion");
            add("Distract");
            add("Dominate");
            add("Invisibility");
            add("Charm");
            add("Befriend");
            add("Disenchant");
            add("Charisma");
            add("Replica");
            add("Chain Charm");
            add("Reflect");
        }};
    }
}
