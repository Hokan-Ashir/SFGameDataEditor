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
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Pain", 0l));
            add(new Pair<>("Aura of Weakness", 0l));
            add(new Pair<>("Extinct", 0l));
            add(new Pair<>("Death", 0l));
            add(new Pair<>("Pestilence", 0l));
            add(new Pair<>("Area Pain", 0l));
            add(new Pair<>("Aura of Suffocation", 0l));
            add(new Pair<>("Suicide", 0l));
            add(new Pair<>("Chain pain", 0l));
            add(new Pair<>("Cannibalism", 0l));
            add(new Pair<>("Torture", 0l));
        }};
    }
}
