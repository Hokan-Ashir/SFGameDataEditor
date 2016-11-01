package sfgamedataeditor.views.main.modules.spells.schools.spells;

import java.util.List;

public class SpellModelParameter {
    private List<String> listOfSpells;

    public SpellModelParameter(List<String> listOfSpells) {
        this.listOfSpells = listOfSpells;
    }

    public List<String> getListOfSpells() {
        return listOfSpells;
    }
}
