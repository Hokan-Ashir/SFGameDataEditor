package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModelParameter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;
import java.util.ResourceBundle;

public class WeaponsTypesController extends AbstractModulesController<ModuleParameter, WeaponsTypesListView, WeaponPiecesModel> {

    public WeaponsTypesController(WeaponsTypesListView view) {
        super(view);
    }

    @Override
    protected WeaponPiecesModel createModel() {
        String selectedArmorPieceType = getView().getSelectedModuleValue();
        String itemPieceId = String.valueOf(ViewTools.getKeyByPropertyValue(selectedArmorPieceType, I18NTypes.COMMON));
        ResourceBundle itemPiecesBundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEM_PIECES_NAME_MAPPING);
        String itemPieceType = itemPiecesBundle.getString(itemPieceId);
        List<String> itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(Integer.parseInt(itemPieceType));
        WeaponPiecesModelParameter parameter = new WeaponPiecesModelParameter(itemNames, null);
        return new WeaponPiecesModel(parameter);
    }

    @Override
    public void updateView() {
        if (getModel() == null) {
            setModulesComboBoxValue(null);
            return;
        }

        String moduleName = getModel().getParameter().getModuleName();
        if (isElementExistsInComboBox(moduleName)) {
            setModulesComboBoxValue(moduleName);
        } else {
            setModulesComboBoxValue(null);
        }
    }
}
