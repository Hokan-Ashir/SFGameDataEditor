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
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Shock", 0l));
            add(new Pair<>("Confuse", 0l));
            add(new Pair<>("Hypnotize", 0l));
            add(new Pair<>("Amok", 0l));
            add(new Pair<>("Shockwave", 0l));
            add(new Pair<>("Disrupt", 0l));
            add(new Pair<>("Aura of Hypnosis", 0l));
            add(new Pair<>("Demoralize", 0l));
            add(new Pair<>("Chain Shock", 0l));
            add(new Pair<>("Area Hypnosis", 0l));
            add(new Pair<>("Area Confuse", 0l));
        }};
    }
}
