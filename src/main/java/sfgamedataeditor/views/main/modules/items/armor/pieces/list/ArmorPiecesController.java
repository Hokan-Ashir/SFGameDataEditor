package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.database.items.armor.parameters.ArmorParametersObject;
import sfgamedataeditor.database.items.armor.parameters.ArmorParametersTableService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.items.requirements.ItemRequirementsObject;
import sfgamedataeditor.database.items.requirements.ItemRequirementsTableService;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersModelParameter;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class ArmorPiecesController extends AbstractModulesController<ArmorPiecesModelParameter, ArmorPiecesView, ArmorParametersModel> {

    public ArmorPiecesController(ArmorPiecesView view) {
        super(view);
    }

    @Override
    protected ArmorParametersModel createModel() {
        int itemId = getItemId();

        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        ArmorParametersObject armorParametersObject = ArmorParametersTableService.INSTANCE.getObjectByItemId(itemId);
        ItemRequirementsObject requirementsObject = ItemRequirementsTableService.INSTANCE.getObjectByItemId(itemId);
        ArmorParametersModelParameter parameter = new ArmorParametersModelParameter(itemPriceObject, armorParametersObject, requirementsObject);
        return new ArmorParametersModel(parameter);
    }

    private int getItemId() {
        String selectedArmorPiece = getView().getSelectedModuleValue();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.ITEMS);
        int itemId = 0;
        Set<String> keySet = bundle.keySet();
        for (String key : keySet) {
            if (bundle.getString(key).equals(selectedArmorPiece)) {
                itemId = Integer.parseInt(key);
                break;
            }
        }
        return itemId;
    }

    @Override
    public void updateView() {
        List<String> armorPiecesNames = getModel().getParameter().getArmorPiecesNames();
        getView().clearComboBoxAndMapping();

        for (String armorPiece : armorPiecesNames) {
            getView().addMapping(armorPiece, ArmorParametersView.class);
        }

        getView().reinitializeComboBox();
        setModulesComboBoxValue(getModel().getParameter().getSelectedArmorPieceName());
    }
}
