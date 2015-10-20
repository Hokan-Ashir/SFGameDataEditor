package sfgamedataeditor.spells.whitemagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class BoonsView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            // Quickness = 48
            // QuicknessArea = 49
            // FastFighting = 118
            add(new Pair<>("Aura of Fast Fighting", 110));
            add(new Pair<>("Hallow", 58));
            // Dexterity = 116
            add(new Pair<>("Aura of Dexterity", 115));
            add(new Pair<>("Dispel Black Aura", 112));
            add(new Pair<>("Aura of Fast Waling", 113));
            add(new Pair<>("Aura of Light", 114));
            // Flexibility = 50
            // FlexibilityArea = 51
            add(new Pair<>("Aura of Flexibility", 111));
            add(new Pair<>("Invulnerability", 6));
            // HallowChained = 230
            add(new Pair<>("Chain Hallow", 190));
            // Eternity = 229
            add(new Pair<>("Aura of Eternity", 192));
            // HolyMight = 57
            add(new Pair<>("Reinforcement", 191));
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
        return 3;
    }
}
