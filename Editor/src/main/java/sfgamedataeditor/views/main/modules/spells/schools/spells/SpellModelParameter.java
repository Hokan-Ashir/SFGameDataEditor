package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.util.Set;

public class SpellModelParameter extends AbstractSubModuleParameter {

    public SpellModelParameter(Set<String> objectNames, String selectedObjectName) {
        super(objectNames, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return SpellParameterView.class;
    }
}
