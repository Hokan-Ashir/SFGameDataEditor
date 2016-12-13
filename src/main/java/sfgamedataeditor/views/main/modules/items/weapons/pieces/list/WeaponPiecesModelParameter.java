package sfgamedataeditor.views.main.modules.items.weapons.pieces.list;

import java.util.List;

public class WeaponPiecesModelParameter {
    private final List<String> weaponPiecesNames;
    private final String selectedWeaponPieceName;

    public WeaponPiecesModelParameter(List<String> weaponPiecesNames, String selectedWeaponPieceName) {
        this.weaponPiecesNames = weaponPiecesNames;
        this.selectedWeaponPieceName = selectedWeaponPieceName;
    }

    public List<String> getWeaponPiecesNames() {
        return weaponPiecesNames;
    }

    public String getSelectedWeaponPieceName() {
        return selectedWeaponPieceName;
    }
}
