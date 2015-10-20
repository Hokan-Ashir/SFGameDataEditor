package sfgamedataeditor.spells.whitemagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class LifeView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Healing", 2));
            add(new Pair<>("Area Healing", 43));
            // Strengthen = 52
            // StrengthenArea = 53
            add(new Pair<>("Aura of Strength", 102));
            add(new Pair<>("Greater Healing", 45));
            // HealingAura = 166
            add(new Pair<>("Aura of Healing", 103));
            // Endurance = 117
            add(new Pair<>("Aura of Endurance", 104));
            add(new Pair<>("Sentinel Healing", 44));
            add(new Pair<>("Suicide Healing", 105));
            // Guard = 54
            add(new Pair<>("Assistance", 184));
            add(new Pair<>("Revenge", 186));
            add(new Pair<>("Holy Touch", 185));
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
        return 1;
    }
}
