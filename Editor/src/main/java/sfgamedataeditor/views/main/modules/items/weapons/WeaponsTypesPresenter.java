package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.common.widgets.items.weapons.type.WeaponTypesMap;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModelParameter;

import java.util.Set;

public class WeaponsTypesPresenter extends AbstractModulesPresenter<ModuleParameter, WeaponsTypesListView, WeaponPiecesModel> {

    public WeaponsTypesPresenter(WeaponsTypesListView view) {
        super(view);
    }

    @Override
    protected WeaponPiecesModel createModel() {
        String selectedWeaponPieceType = getView().getSelectedModuleName();
        Integer weaponTypeId = WeaponTypesMap.INSTANCE.getWeaponTypeByName(selectedWeaponPieceType);
        Set<String> itemNames = WeaponParametersTableService.INSTANCE.getItemsByItemType(weaponTypeId);
        WeaponPiecesModelParameter parameter = new WeaponPiecesModelParameter(itemNames, null);
        return new WeaponPiecesModel(parameter);
    }
}
