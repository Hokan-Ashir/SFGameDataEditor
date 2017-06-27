package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterView;

import java.util.List;

public class SpellModelParameter extends AbstractSubModuleParameter {

    public SpellModelParameter(List<ObjectTuple> panelTuples, ObjectTuple selectedObjectName) {
        super(panelTuples, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return SpellParameterView.class;
    }
}
