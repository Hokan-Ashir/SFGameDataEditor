package sfgamedataeditor.views.main.modules.items.weapons.pieces.list;

import sfgamedataeditor.mvc.objects.SubModuleParameter;

import java.util.List;

public class WeaponPiecesModelParameter implements SubModuleParameter {
    private final List<String> weaponPiecesNames;
    private final String selectedWeaponPieceName;

    public WeaponPiecesModelParameter(List<String> weaponPiecesNames, String selectedWeaponPieceName) {
        this.weaponPiecesNames = weaponPiecesNames;
        this.selectedWeaponPieceName = selectedWeaponPieceName;
    }

    public List<String> getWeaponPiecesNames() {
        return weaponPiecesNames;
    }

    @Override
    public String getSelectedModuleName() {
        return selectedWeaponPieceName;
    }
}
