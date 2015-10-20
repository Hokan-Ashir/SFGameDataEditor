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
            add(new Pair<>("Self Illusion", 63));
            // Forget = 62
            // Retention = 64
            add(new Pair<>("Distract", 119));
            add(new Pair<>("Dominate", 120));
            add(new Pair<>("Invisibility", 86));
            add(new Pair<>("Charm", 122));
            add(new Pair<>("Befriend", 123));
            add(new Pair<>("Disenchant", 124));
            add(new Pair<>("Charisma", 125));
            // ObjectIllusion = 121
            add(new Pair<>("Mirror Image", 211));
            // CharmChained = 237
            add(new Pair<>("Chain Charm", 212));
            add(new Pair<>("Voodoo", 213));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass() {
        return 6;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass() {
        return 2;
    }
}
