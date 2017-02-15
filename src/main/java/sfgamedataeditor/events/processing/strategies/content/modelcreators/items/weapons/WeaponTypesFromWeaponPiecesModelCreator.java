package sfgamedataeditor.events.processing.strategies.content.modelcreators.items.weapons;

import sfgamedataeditor.common.widgets.items.weapons.type.WeaponTypesMap;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModel;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class WeaponTypesFromWeaponPiecesModelCreator implements ModelCreator<ModulesModel, WeaponPiecesModel> {

    @Override
    public ModulesModel createModel(WeaponPiecesModel childModel) {
        // TODO maybe it is important to get not first one object
        // TODO remove duplication and generalize item types views
        String selectedWeaponPieceName = childModel.getParameter().getSubPanelsNames().iterator().next();
        int itemId = ViewTools.getKeyByPropertyValue(selectedWeaponPieceName, I18NTypes.ITEMS);

        Integer itemPieceTypeId = WeaponParametersTableService.INSTANCE.getObjectByItemId(itemId).type;
        String weaponPieceTypeName = WeaponTypesMap.INSTANCE.getWeaponTypeNameById(itemPieceTypeId);
        ModuleParameter parameter = new ModuleParameter(weaponPieceTypeName);
        return new ModulesModel(parameter);
    }
}
