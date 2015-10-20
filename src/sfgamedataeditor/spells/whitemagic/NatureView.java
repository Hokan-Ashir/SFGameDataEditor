package sfgamedataeditor.spells.whitemagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class NatureView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            // RemoveCurse = 55
            // ThornShieldDamage = 61
            add(new Pair<>("Thorn Shield", 47));
            add(new Pair<>("Cure Disease", 24));
            add(new Pair<>("Summon Wolf", 106));
            // Regenerate = 56
            add(new Pair<>("Aura of Regeneration", 107));
            add(new Pair<>("Charm Animal", 46));
            add(new Pair<>("Cure Poison", 7));
            add(new Pair<>("Summon Bear", 109));
            add(new Pair<>("Dominate Animal", 108));
            add(new Pair<>("Summon Tree Wrath", 188));
            add(new Pair<>("Roots", 189));
            add(new Pair<>("Area Roots", 187));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass() {
        return 4;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass() {
        return 2;
    }
}
