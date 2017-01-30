package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.mvc.objects.SubModuleParameter;

import java.util.List;

public class ArmorPiecesModelParameter implements SubModuleParameter {
    private final List<String> armorPiecesNames;
    private final String selectedArmorPieceName;

    public ArmorPiecesModelParameter(List<String> armorPiecesNames, String selectedArmorPieceName) {
        this.armorPiecesNames = armorPiecesNames;
        this.selectedArmorPieceName = selectedArmorPieceName;
    }

    public List<String> getArmorPiecesNames() {
        return armorPiecesNames;
    }

    @Override
    public String getSelectedModuleName() {
        return selectedArmorPieceName;
    }
}
