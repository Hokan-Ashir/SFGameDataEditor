package sfgamedataeditor.spells.elementalmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class ElementalMagicView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            // EssenceElementalEffect = 183
            add(new Pair<>("Elemental Essence", 176));
            // AlmightinessElementalEffect = 182
            add(new Pair<>("Elemental Almightiness", 180));
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
        return 0;
    }
}
