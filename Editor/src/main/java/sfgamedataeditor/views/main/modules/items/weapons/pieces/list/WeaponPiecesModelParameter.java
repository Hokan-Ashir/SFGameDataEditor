package sfgamedataeditor.views.main.modules.items.weapons.pieces.list;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.mvc.objects.SubModuleParameter;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersView;

import java.util.Set;

public class WeaponPiecesModelParameter implements SubModuleParameter {
    private final Set<String> weaponPiecesNames;
    private final String selectedWeaponPieceName;

    public WeaponPiecesModelParameter(Set<String> weaponPiecesNames, String selectedWeaponPieceName) {
        this.weaponPiecesNames = weaponPiecesNames;
        this.selectedWeaponPieceName = selectedWeaponPieceName;
    }

    @Override
    public String getSelectedModuleName() {
        return selectedWeaponPieceName;
    }

    @Override
    public Set<String> getSubPanelsNames() {
        return weaponPiecesNames;
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return WeaponParametersView.class;
    }
}
