package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;
import sfgamedataeditor.views.main.modules.merchants.inventory.items.models.ArmorModelCreator;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class ArmorPiecesController extends AbstractModulesController<ArmorPiecesModelParameter, ArmorPiecesView, ArmorParametersModel> {

    private ArmorModelCreator modelCreator = new ArmorModelCreator();

    public ArmorPiecesController(ArmorPiecesView view) {
        super(view);
    }

    @Override
    protected ArmorParametersModel createModel() {
        int itemId = getItemId();
        return modelCreator.createModel(itemId);
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
