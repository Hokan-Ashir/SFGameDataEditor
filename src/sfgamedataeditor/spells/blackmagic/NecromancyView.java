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
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Summon Skeleton", 0l));
            add(new Pair<>("Lifetap", 0l));
            add(new Pair<>("Death Grasp", 0l));
            add(new Pair<>("Summon Undead Goblin", 0l));
            add(new Pair<>("Raise Dead", 0l));
            add(new Pair<>("Aura of Lifetap", 0l));
            add(new Pair<>("Summon Spectre", 0l));
            add(new Pair<>("Trick Death", 0l));
            add(new Pair<>("Chain Lifetap", 0l));
            add(new Pair<>("Summon Blade", 0l));
            add(new Pair<>("Control Undead", 0l));
        }};
    }
}
