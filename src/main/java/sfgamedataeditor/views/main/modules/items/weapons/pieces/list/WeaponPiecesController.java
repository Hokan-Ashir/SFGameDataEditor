package sfgamedataeditor.views.main.modules.items.weapons.pieces.list;

import sfgamedataeditor.database.items.effects.ItemEffectsObject;
import sfgamedataeditor.database.items.effects.ItemEffectsTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsTableService;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersObject;
import sfgamedataeditor.database.items.weapon.parameters.WeaponParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersModel;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersModelParameter;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.parameters.WeaponParametersView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class WeaponPiecesController extends AbstractModulesController<WeaponPiecesModelParameter, WeaponPiecesView, WeaponParametersModel> {

    public WeaponPiecesController(WeaponPiecesView view) {
        super(view);
    }

    @Override
    protected WeaponParametersModel createModel() {
        int itemId = getItemId();

        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        ItemEffectsObject itemEffectsObject = ItemEffectsTableService.INSTANCE.getObjectByItemId(itemId);
        WeaponParametersObject weaponParametersObject = WeaponParametersTableService.INSTANCE.getObjectByItemId(itemId);
        ItemRequirementsObject requirementsObject = ItemRequirementsTableService.INSTANCE.getObjectByItemId(itemId);
        WeaponParametersModelParameter parameter = new WeaponParametersModelParameter(itemPriceObject, itemEffectsObject, weaponParametersObject, requirementsObject);
        return new WeaponParametersModel(parameter);
    }

    private int getItemId() {
        String selectedWeaponPiece = getView().getSelectedModuleValue();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEMS);
        int itemId = 0;
        Set<String> keySet = bundle.keySet();
        for (String key : keySet) {
            if (bundle.getString(key).equals(selectedWeaponPiece)) {
                itemId = Integer.parseInt(key);
                break;
            }
        }
        return itemId;
    }

    @Override
    public void updateView() {
        List<String> weaponPiecesNames = getModel().getParameter().getWeaponPiecesNames();
        getView().clearComboBoxAndMapping();

        for (String weaponPiece : weaponPiecesNames) {
            getView().addMapping(weaponPiece, WeaponParametersView.class);
        }

        getView().reinitializeComboBox();
        setModulesComboBoxValue(getModel().getParameter().getSelectedWeaponPieceName());
    }
}
