package sfgamedataeditor.views.main.modules.items.weapons.pieces.list;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersView;

import java.util.Set;

public class WeaponPiecesModelParameter extends AbstractSubModuleParameter {

    public WeaponPiecesModelParameter(Set<String> objectNames, String selectedObjectName) {
        super(objectNames, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return WeaponParametersView.class;
    }
}
