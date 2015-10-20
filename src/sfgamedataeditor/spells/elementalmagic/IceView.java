package sfgamedataeditor.spells.elementalmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class IceView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Iceburst", 0));
            add(new Pair<>("Iceshield", 0));
            add(new Pair<>("Freeze", 0));
            add(new Pair<>("Fog", 0));
            add(new Pair<>("Summon Ice Elemental", 0));
            add(new Pair<>("Wave of Ice", 0));
            add(new Pair<>("Chill Resistance", 0));
            add(new Pair<>("Blizzard", 0));
            add(new Pair<>("Chain Iceburst", 0));
            add(new Pair<>("Area Freeze", 0));
            add(new Pair<>("Summon Ice Golem", 0));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellClass() {
        return 5;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getSpellSubClass() {
        return 2;
    }
}
