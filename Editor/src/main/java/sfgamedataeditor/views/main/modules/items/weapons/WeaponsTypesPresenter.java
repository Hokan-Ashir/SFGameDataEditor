package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.common.widgets.items.weapons.type.WeaponTypesMap;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersTableService;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModelParameter;

import java.util.List;

public class WeaponsTypesPresenter extends AbstractModulesPresenter<ModuleParameter, WeaponsTypesListView, WeaponPiecesModel> {

    public WeaponsTypesPresenter(WeaponsTypesListView view) {
        super(view);
    }

    @Override
    protected WeaponPiecesModel createModel() {
        String selectedWeaponPieceType = getView().getSelectedModuleName();
        Integer weaponTypeId = WeaponTypesMap.INSTANCE.getWeaponTypeByName(selectedWeaponPieceType);
        List<ObjectTuple> itemNames = WeaponParametersTableService.INSTANCE.getItemsByItemType(weaponTypeId);
        if (itemNames == null || itemNames.isEmpty()) {
            itemNames = ArmorParametersTableService.INSTANCE.getOrbNames();
        }
        WeaponPiecesModelParameter parameter = new WeaponPiecesModelParameter(itemNames, null);
        return new WeaponPiecesModel(parameter);
    }
}
