package views.spells.blackmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class NecromancyView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Summon Skeleton");
            add("Lifetap");
            add("Death Grasp");
            add("Summon Undead Goblin");
            add("Raise Dead");
            add("Aura of Lifetap");
            add("Summon Spectre");
            add("Trick Death");
            add("Chain Lifetap");
            add("Summon Blade");
            add("Control Undead");
        }};
    }
}
