package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import java.util.List;

public class ArmorPiecesModelParameter {
    private final List<String> armorPiecesNames;
    private final String selectedArmorPieceName;

    public ArmorPiecesModelParameter(List<String> armorPiecesNames, String selectedArmorPieceName) {
        this.armorPiecesNames = armorPiecesNames;
        this.selectedArmorPieceName = selectedArmorPieceName;
    }

    public List<String> getArmorPiecesNames() {
        return armorPiecesNames;
    }

    public String getSelectedArmorPieceName() {
        return selectedArmorPieceName;
    }
}
