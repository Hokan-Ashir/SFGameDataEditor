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
            add(new Pair<>("Pain", 18));
            // Weaken = 35
            // WeakenArea = 39
            add(new Pair<>("Aura of Weakness", 88));
            add(new Pair<>("Extinct", 81));
            add(new Pair<>("Death", 3));
            // PlagueArea = 40
            add(new Pair<>("Pestilence", 23));
            add(new Pair<>("Area Pain", 28));
            // Suffocation = 99
            add(new Pair<>("Aura of Suffocation", 89));
            add(new Pair<>("Suicide Death", 90));
            // PainChained = 240
            add(new Pair<>("Chain pain", 193));
            add(new Pair<>("Cannibalize", 194));
            add(new Pair<>("Torture", 195));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass() {
        return 7;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass() {
        return 1;
    }
}
