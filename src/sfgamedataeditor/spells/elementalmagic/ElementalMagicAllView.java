package sfgamedataeditor.spells.elementalmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class ElementalMagicAllView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
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
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass2() {
        return 5;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass2() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass3() {
        return 5;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass3() {
        return 3;
    }
}
