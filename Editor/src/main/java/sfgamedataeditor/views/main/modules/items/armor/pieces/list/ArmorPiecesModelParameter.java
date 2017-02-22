package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;

import java.util.Set;

public class ArmorPiecesModelParameter extends AbstractSubModuleParameter {

    public ArmorPiecesModelParameter(Set<String> objectNames, String selectedObjectName) {
        super(objectNames, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return ArmorParametersView.class;
    }
}
