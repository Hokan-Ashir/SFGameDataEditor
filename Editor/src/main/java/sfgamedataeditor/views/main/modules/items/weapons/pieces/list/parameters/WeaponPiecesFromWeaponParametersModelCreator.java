package sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters;

import sfgamedataeditor.database.items.armor.parameters.ArmorParametersTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class WeaponPiecesFromWeaponParametersModelCreator implements ModelCreator<WeaponPiecesModel, WeaponParametersModel> {
    @Override
    public WeaponPiecesModel createModel(WeaponParametersModel childModel) {
        List<SubViewPanelTuple> itemNames;

        if (childModel.getParameter().getArmorParametersObject() != null
                && childModel.getParameter().getWeaponParametersObject() == null) {
            itemNames = ArmorParametersTableService.INSTANCE.getOrbNames();
        } else {

            Integer typeId = childModel.getParameter().getPriceParametersObject().typeId;
            itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(typeId);
        }
        Integer nameId = childModel.getParameter().getPriceParametersObject().nameId;
        String selectedItem = TextTableService.INSTANCE.getObjectName(nameId);
        WeaponPiecesModelParameter parameter = new WeaponPiecesModelParameter(itemNames, selectedItem);
        return new WeaponPiecesModel(parameter);
    }
}
