package sfgamedataeditor.spells.blackmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class NecromancyView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Summon Skeleton", 0));
            add(new Pair<>("Lifetap", 0));
            add(new Pair<>("Death Grasp", 0));
            add(new Pair<>("Summon Undead Goblin", 0));
            add(new Pair<>("Raise Dead", 0));
            add(new Pair<>("Aura of Lifetap", 0));
            add(new Pair<>("Summon Spectre", 0));
            add(new Pair<>("Trick Death", 0));
            add(new Pair<>("Chain Lifetap", 0));
            add(new Pair<>("Summon Blade", 0));
            add(new Pair<>("Control Undead", 0));
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
        return 2;
    }
}
