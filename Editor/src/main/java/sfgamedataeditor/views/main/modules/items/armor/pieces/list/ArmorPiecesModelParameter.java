package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.mvc.objects.SubModuleParameter;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;

import java.util.Set;

public class ArmorPiecesModelParameter implements SubModuleParameter {
    private final Set<String> armorPiecesNames;
    private final String selectedArmorPieceName;

    public ArmorPiecesModelParameter(Set<String> armorPiecesNames, String selectedArmorPieceName) {
        this.armorPiecesNames = armorPiecesNames;
        this.selectedArmorPieceName = selectedArmorPieceName;
    }

    @Override
    public String getSelectedModuleName() {
        return selectedArmorPieceName;
    }

    @Override
    public Set<String> getSubPanelsNames() {
        return armorPiecesNames;
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return ArmorParametersView.class;
    }
}
