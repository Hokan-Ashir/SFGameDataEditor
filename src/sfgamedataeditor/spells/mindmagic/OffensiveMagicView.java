package sfgamedataeditor.spells.mindmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class OffensiveMagicView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Shock", 69));
            add(new Pair<>("Confuse", 72));
            // HypnotizeTwo = 167
            add(new Pair<>("Hypnotize", 21));
            add(new Pair<>("Amok", 79));
            add(new Pair<>("Shockwave", 126));
            add(new Pair<>("Disrupt", 70));
            add(new Pair<>("Aura of Hypnotize", 127));
            // Fear = 71
            add(new Pair<>("Demoralize", 128));
            // ShockChained = 238
            add(new Pair<>("Chain Shock", 214));
            add(new Pair<>("Area Hypnotize", 215));
            add(new Pair<>("Area Confuse", 216));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass() {
        return 6;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass() {
        return 2;
    }
}
