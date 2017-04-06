package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class ArmorPiecesPresenter extends AbstractModulesPresenter<ArmorPiecesModelParameter, ArmorPiecesView, ArmorParametersModel> {

    private static final Integer[] ARMOR_TYPES_IDS = new Integer[] {
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.helmets")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.chest.armor")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.robes")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.legs.armor")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.shield")),
            Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, "items.armor.rings"))
    };

    private final ArmorModelCreator modelCreator = new ArmorModelCreator();

    public ArmorPiecesPresenter(ArmorPiecesView view) {
        super(view);
    }

    @Override
    protected ArmorParametersModel createModel() {
        String selectedArmorPiece = getView().getSelectedModuleName();
        int itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(selectedArmorPiece, ARMOR_TYPES_IDS);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }
}
