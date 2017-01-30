package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.mvc.objects.SubModuleParameter;

import java.util.List;

public class SpellModelParameter implements SubModuleParameter {
    private final List<String> listOfSpells;
    private final String selectedSpell;

    public SpellModelParameter(List<String> listOfSpells, String selectedSpell) {
        this.listOfSpells = listOfSpells;
        this.selectedSpell = selectedSpell;
    }

    public List<String> getListOfSpells() {
        return listOfSpells;
    }

    @Override
    public String getSelectedModuleName() {
        return selectedSpell;
    }
}
