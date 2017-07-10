package sfgamedataeditor.views.main.modules.items.weapons.pieces.list;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class WeaponPiecesPresenter extends AbstractModulesPresenter<WeaponPiecesModelParameter, WeaponPiecesView, WeaponParametersModel> {

    private static final Integer[] WEAPON_TYPE_IDS = new Integer[]{
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.1h.weapon")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.2h.weapon")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.bow")),
    };

    private final WeaponModelCreator modelCreator = new WeaponModelCreator();

    public WeaponPiecesPresenter(WeaponPiecesView view) {
        super(view);
    }

    @Override
    protected WeaponParametersModel createModel() {
        String selectedWeaponPiece = getView().getSelectedModuleName();
        int itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(selectedWeaponPiece, WEAPON_TYPE_IDS);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId);
    }
}
