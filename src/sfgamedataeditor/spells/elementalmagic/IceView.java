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
            // IceArrowEffect = 168
            add(new Pair<>("Iceburst", 14));
            // IceShieldStun = 22
            add(new Pair<>("Iceshield", 15));
            add(new Pair<>("Freeze", 9));
            add(new Pair<>("Fog", 10));
            add(new Pair<>("Summon Ice Elemental", 136));
            add(new Pair<>("Wave of Ice", 137));
            // IceBlockEffect = 169
            add(new Pair<>("Chill Resistance", 138));
            // AcidCloud = 75
            add(new Pair<>("Blizzard", 74));
            // IceBurstChained = 235
            add(new Pair<>("Chain Iceburst", 205));
            add(new Pair<>("Area Freeze", 207));
            add(new Pair<>("Summon Ice Golem", 206));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass() {
        return 5;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass() {
        return 2;
    }
}
