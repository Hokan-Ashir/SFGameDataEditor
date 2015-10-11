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
    protected List<Pair<String, Long>> createSpellList() {
        return new ArrayList<Pair<String, Long>>() {{
            add(new Pair<>("Iceburst", 0l));
            add(new Pair<>("Iceshield", 0l));
            add(new Pair<>("Freeze", 0l));
            add(new Pair<>("Fog", 0l));
            add(new Pair<>("Summon Ice Elemental", 0l));
            add(new Pair<>("Wave of Ice", 0l));
            add(new Pair<>("Chill Resistance", 0l));
            add(new Pair<>("Blizzard", 0l));
            add(new Pair<>("Chain Iceburst", 0l));
            add(new Pair<>("Area Freeze", 0l));
            add(new Pair<>("Summon Ice Golem", 0l));
        }};
    }
}
