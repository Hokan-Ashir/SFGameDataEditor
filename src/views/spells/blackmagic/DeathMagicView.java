package views.spells.blackmagic;

import views.spells.SpellClassView;

import java.util.ArrayList;
import java.util.List;

public class DeathMagicView extends SpellClassView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<String> createSpellList() {
        return new ArrayList<String>() {{
            add("Pain");
            add("Aura of Weakness");
            add("Extinct");
            add("Death");
            add("Pestilence");
            add("Area Pain");
            add("Aura of Suffocation");
            add("Suicide");
            add("Chain pain");
            add("Cannibalism");
            add("Torture");
        }};
    }
}
