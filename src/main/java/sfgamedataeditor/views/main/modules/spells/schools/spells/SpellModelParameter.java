package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.mvc.objects.SubModuleParameter;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.util.Set;

public class SpellModelParameter implements SubModuleParameter {
    private final Set<String> listOfSpells;
    private final String selectedSpell;

    public SpellModelParameter(Set<String> listOfSpells, String selectedSpell) {
        this.listOfSpells = listOfSpells;
        this.selectedSpell = selectedSpell;
    }

    @Override
    public String getSelectedModuleName() {
        return selectedSpell;
    }

    @Override
    public Set<String> getSubPanelsNames() {
        return listOfSpells;
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return SpellParameterView.class;
    }
}
