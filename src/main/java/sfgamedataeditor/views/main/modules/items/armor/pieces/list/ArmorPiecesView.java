package sfgamedataeditor.views.main.modules.items.armor.pieces.list;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ArmorPiecesView extends AbstractModulesView {

    public ArmorPiecesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.ARMOR_GUI, "items"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
    }

    public void clearComboBoxAndMapping() {
        getModulesComboBox().removeAllItems();
        getComboBoxMapping().clear();
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return ArmorPiecesController.class;
    }
}
