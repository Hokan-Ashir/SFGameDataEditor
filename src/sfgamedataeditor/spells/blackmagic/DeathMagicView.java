package sfgamedataeditor.spells.blackmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class DeathMagicView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Pain", 0));
            add(new Pair<>("Aura of Weakness", 0));
            add(new Pair<>("Extinct", 0));
            add(new Pair<>("Death", 0));
            add(new Pair<>("Pestilence", 0));
            add(new Pair<>("Area Pain", 0));
            add(new Pair<>("Aura of Suffocation", 0));
            add(new Pair<>("Suicide", 0));
            add(new Pair<>("Chain pain", 0));
            add(new Pair<>("Cannibalism", 0));
            add(new Pair<>("Torture", 0));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellClass() {
        return 7;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellSubClass() {
        return 1;
    }
}
