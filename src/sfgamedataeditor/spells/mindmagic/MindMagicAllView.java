package sfgamedataeditor.spells.mindmagic;

import javafx.util.Pair;
import sfgamedataeditor.views.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class MindMagicAllView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Pair<String, Integer>> createSpellList() {
        return new ArrayList<Pair<String, Integer>>() {{
            add(new Pair<>("Mind Almightiness", 181));
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
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellClass2() {
        return 6;
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
        return 6;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte getSpellSubClass3() {
        return 3;
    }
}
