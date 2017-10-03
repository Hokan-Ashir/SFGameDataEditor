package sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters;

import sfgamedataeditor.database.items.armor.parameters.ArmorParametersTableService;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModelParameter;

import java.util.List;

public class WeaponPiecesFromWeaponParametersModelCreator implements ModelCreator<WeaponPiecesModel, WeaponParametersModel> {
    @Override
    public WeaponPiecesModel createModel(WeaponParametersModel childModel) {
        List<ObjectTuple> itemNames;

        if (childModel.getParameter().getArmorParametersObject() != null
                && childModel.getParameter().getWeaponParametersObject() == null) {
            itemNames = ArmorParametersTableService.INSTANCE.getOrbNames();
        } else {
            Integer typeId = childModel.getParameter().getWeaponParametersObject().type;
            itemNames = WeaponParametersTableService.INSTANCE.getItemsByItemType(typeId);
        }
        Integer nameId = childModel.getParameter().getPriceParametersObject().nameId;
        ObjectTuple selectedItem = TextTableService.INSTANCE.getObjectTuple(nameId, nameId);
        WeaponPiecesModelParameter parameter = new WeaponPiecesModelParameter(itemNames, selectedItem);
        return new WeaponPiecesModel(parameter);
    }
}
