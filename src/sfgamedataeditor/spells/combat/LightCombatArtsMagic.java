package sfgamedataeditor.spells.combat;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class LightCombatArtsMagic extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            // Berserk = 85
            // AbilityBerserk = 152
            add(new Pair<>("Berserk", 152));
            add(new Pair<>("Blessing", 153));
            // AbilityShelter = 154
            add(new Pair<>("Sanctuary", 154));
            // AbilityDurability = 155
            add(new Pair<>("Endurance", 155));
            add(new Pair<>("Critical Hits", 222));
            add(new Pair<>("Shift Life", 220));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass() {
        return 0;
    }
}
