package sfgamedataeditor.spells.mindmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Self Illusion", 0));
            add(new Pair<>("Distract", 0));
            add(new Pair<>("Dominate", 0));
            add(new Pair<>("Invisibility", 0));
            add(new Pair<>("Charm", 0));
            add(new Pair<>("Befriend", 0));
            add(new Pair<>("Disenchant", 0));
            add(new Pair<>("Charisma", 0));
            add(new Pair<>("Replica", 0));
            add(new Pair<>("Chain Charm", 0));
            add(new Pair<>("Reflect", 0));
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
        return 2;
    }
}
