package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesModelParameter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ResourceBundle;
import java.util.Set;

public class WeaponsTypesPresenter extends AbstractModulesPresenter<ModuleParameter, WeaponsTypesListView, WeaponPiecesModel> {

    public WeaponsTypesPresenter(WeaponsTypesListView view) {
        super(view);
    }

    @Override
    protected WeaponPiecesModel createModel() {
        String selectedArmorPieceType = getView().getSelectedModuleValue();
        String itemPieceId = ViewTools.getKeyStringByPropertyValue(selectedArmorPieceType, I18NTypes.COMMON);
        ResourceBundle itemPiecesBundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEM_PIECES_NAME_MAPPING);
        String itemPieceType = itemPiecesBundle.getString(itemPieceId);
        Set<String> itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(Integer.parseInt(itemPieceType));
        WeaponPiecesModelParameter parameter = new WeaponPiecesModelParameter(itemNames, null);
        return new WeaponPiecesModel(parameter);
    }
}
