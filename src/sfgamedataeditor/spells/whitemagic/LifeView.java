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
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Healing", 0l));
            add(new Pair<>("Area Healing", 0l));
            add(new Pair<>("Aura of Strength", 0l));
            add(new Pair<>("Greater Healing", 0l));
            add(new Pair<>("Aura of Healing", 0l));
            add(new Pair<>("Aura of Endurance", 0l));
            add(new Pair<>("Sentiel Healing", 0l));
            add(new Pair<>("Suicide Healing", 0l));
            add(new Pair<>("Help", 0l));
            add(new Pair<>("Vengeance", 0l));
            add(new Pair<>("Healing Touch", 0l));
        }};
    }
}
