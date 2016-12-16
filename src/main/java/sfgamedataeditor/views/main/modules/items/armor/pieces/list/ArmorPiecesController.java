package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersModel;
import sfgamedataeditor.views.main.modules.items.armor.pieces.list.parameters.ArmorParametersView;
import sfgamedataeditor.views.main.modules.merchants.inventory.items.models.ArmorModelCreator;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.List;

public class ArmorPiecesController extends AbstractModulesController<ArmorPiecesModelParameter, ArmorPiecesView, ArmorParametersModel> {

    private ArmorModelCreator modelCreator = new ArmorModelCreator();

    public ArmorPiecesController(ArmorPiecesView view) {
        super(view);
    }

    @Override
    protected ArmorParametersModel createModel() {
        String selectedArmorPiece = getView().getSelectedModuleValue();
        int itemId = ViewTools.getKeyByPropertyValue(selectedArmorPiece, I18NTypes.ITEMS);
        return modelCreator.createModel(itemId);
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
