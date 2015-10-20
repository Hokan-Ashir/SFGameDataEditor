package sfgamedataeditor.spells.combat;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class HeavyCombatArtsMagic extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Battle Cry", 148));
            // AbilityBenefactions = 149
            add(new Pair<>("Support", 149));
            // AbilityPatronize = 150
            add(new Pair<>("Protect", 150));
            // AbilityEndurance = 151
            add(new Pair<>("Steadfastness", 151));
            add(new Pair<>("Shift Life", 220));
            add(new Pair<>("Riposte", 221));
        }};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass() {
        return 0;
    }
}
