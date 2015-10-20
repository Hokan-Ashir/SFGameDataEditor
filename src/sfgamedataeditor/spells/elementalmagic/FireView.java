package sfgamedataeditor.spells.elementalmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class FireView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Fireburst", 1));
            // FireShieldDamage = 60
            add(new Pair<>("Fireshield", 12));
            // FireBallEffect = 147
            add(new Pair<>("Fireball", 13));
            add(new Pair<>("Illuminate", 11));
            add(new Pair<>("Summon Fire Elemental", 133));
            add(new Pair<>("Wave of Fire", 134));
            // FireBlockEffect = 170
            // FireResistance = 173
            add(new Pair<>("Melt Resistance", 135));
            add(new Pair<>("Rain of Fire", 73));
            // FireBurstChained = 234
            add(new Pair<>("Chain Fireburst", 202));
            // FireBallChained = 239
            add(new Pair<>("Chain Fireball", 204));
            add(new Pair<>("Summon Fire Golem", 203));
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
        return 1;
    }
}
