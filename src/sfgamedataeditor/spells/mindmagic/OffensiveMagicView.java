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
            add(new Pair<>("Shock", 0));
            add(new Pair<>("Confuse", 0));
            add(new Pair<>("Hypnotize", 0));
            add(new Pair<>("Amok", 0));
            add(new Pair<>("Shockwave", 0));
            add(new Pair<>("Disrupt", 0));
            add(new Pair<>("Aura of Hypnosis", 0));
            add(new Pair<>("Demoralize", 0));
            add(new Pair<>("Chain Shock", 0));
            add(new Pair<>("Area Hypnosis", 0));
            add(new Pair<>("Area Confuse", 0));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellClass() {
        return 6;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellSubClass() {
        return 1;
    }
}
