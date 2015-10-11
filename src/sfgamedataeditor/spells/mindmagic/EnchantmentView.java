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
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Self Illusion", 0l));
            add(new Pair<>("Distract", 0l));
            add(new Pair<>("Dominate", 0l));
            add(new Pair<>("Invisibility", 0l));
            add(new Pair<>("Charm", 0l));
            add(new Pair<>("Befriend", 0l));
            add(new Pair<>("Disenchant", 0l));
            add(new Pair<>("Charisma", 0l));
            add(new Pair<>("Replica", 0l));
            add(new Pair<>("Chain Charm", 0l));
            add(new Pair<>("Reflect", 0l));
        }};
    }
}
