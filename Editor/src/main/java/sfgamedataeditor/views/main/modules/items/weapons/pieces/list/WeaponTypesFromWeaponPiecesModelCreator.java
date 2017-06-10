package sfgamedataeditor.views.main.modules.items.weapons.pieces.list;

import sfgamedataeditor.common.widgets.items.weapons.type.WeaponTypesMap;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class WeaponTypesFromWeaponPiecesModelCreator implements ModelCreator<ModulesModel, WeaponPiecesModel> {

    private static final Integer[] WEAPON_TYPE_IDS = new Integer[]{
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.1h.weapon")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.2h.weapon")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.bow")),
    };

    // cause "Orb" are technically weapons, from game's perspective, but has no weapons parameters
    // parsed values of Orbs in database have no WeaponParametersObjects, that's why orb has
    // fictive weapon type value and can be explicitly processed
    private static final int ORB_WEAPON_TYPE = 22;

    @Override
    public ModulesModel createModel(WeaponPiecesModel childModel) {
        // TODO remove duplication and generalize item types views
        String selectedWeaponPieceName = childModel.getParameter().getSelectedModuleName();
        if (selectedWeaponPieceName == null) {
            selectedWeaponPieceName = childModel.getParameter().getSubPanelsNames().iterator().next();
        }
        int itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(selectedWeaponPieceName, WEAPON_TYPE_IDS);

        WeaponParametersObject weaponParametersObject = WeaponParametersTableService.INSTANCE.getObjectByItemId(itemId);
        Integer itemPieceTypeId;
        if (weaponParametersObject == null) {
            itemPieceTypeId = ORB_WEAPON_TYPE;
        } else {
            itemPieceTypeId = weaponParametersObject.type;
        }

        String weaponPieceTypeName = WeaponTypesMap.INSTANCE.getWeaponTypeNameById(itemPieceTypeId);
        ModuleParameter parameter = new ModuleParameter(weaponPieceTypeName);
        return new ModulesModel(parameter);
    }
}
