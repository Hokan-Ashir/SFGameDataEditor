package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;

import java.util.List;

public class ArmorPiecesModelParameter extends AbstractSubModuleParameter {

    public ArmorPiecesModelParameter(List<SubViewPanelTuple> objectNames, String selectedObjectName) {
        super(objectNames, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return ArmorParametersView.class;
    }
}
