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
            add(new Pair<>("Summon Skeleton", 29));
            add(new Pair<>("Lifetap", 19));
            add(new Pair<>("Death Grasp", 32));
            add(new Pair<>("Summon Undead Goblin", 20));
            add(new Pair<>("Raise Dead", 30));
            // AuraLifeTap = 91
            // LifeTapAura = 146
            add(new Pair<>("Aura of Lifetap", 146));
            add(new Pair<>("Summon Spectre", 92));
            add(new Pair<>("Feign Death", 93));
            // LifeTapChained = 231
            add(new Pair<>("Chain Lifetap", 196));
            add(new Pair<>("Summon Blade", 198));
            add(new Pair<>("Dominate Undead", 197));
            // Summon Demon = 31
            // SummonChanneler = 33
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
        return 2;
    }
}
