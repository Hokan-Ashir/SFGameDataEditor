package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.ArmorPiecesModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class ArmorTypesController extends AbstractModulesController<ModuleParameter, ArmorTypeListView, ArmorPiecesModel> {

    public ArmorTypesController(ArmorTypeListView view) {
        super(view);
    }

    @Override
    protected ArmorPiecesModel createModel() {
        String selectedArmorPieceType = getView().getSelectedModuleValue();
        ResourceBundle commonBundle = I18NService.INSTANCE.getBundle(I18NTypes.COMMON);
        Set<String> keySet = commonBundle.keySet();
        String selectedKey = null;
        for (String key : keySet) {
            if (commonBundle.getString(key).equals(selectedArmorPieceType)) {
                selectedKey = key;
                break;
            }
        }

        ResourceBundle itemPiecesBundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEM_PIECES_NAME_MAPPING);
        String itemPieceType = itemPiecesBundle.getString(selectedKey);
        List<String> itemNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(Integer.parseInt(itemPieceType));
        ArmorPiecesModelParameter parameter = new ArmorPiecesModelParameter(itemNames, null);
        return new ArmorPiecesModel(parameter);
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
