package sfgamedataeditor.views.main.modules.items.weapons.pieces.list;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersView;

import java.util.List;

public class WeaponPiecesModelParameter extends AbstractSubModuleParameter {

    public WeaponPiecesModelParameter(List<SubViewPanelTuple> panelTuples, String selectedObjectName) {
        super(panelTuples, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return WeaponParametersView.class;
    }
}
