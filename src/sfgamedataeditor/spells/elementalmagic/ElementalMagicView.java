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
            add(new Pair<>("Elemental Essence", 0));
            add(new Pair<>("Elemental Almightiness", 0));
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
        return 0;
    }
}
