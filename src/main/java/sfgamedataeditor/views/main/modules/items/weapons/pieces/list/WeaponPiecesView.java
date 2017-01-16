package sfgamedataeditor.views.main.modules.items.weapons.pieces.list;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class WeaponPiecesView extends AbstractModulesView {

    public WeaponPiecesView() {
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
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return WeaponPiecesPresenter.class;
    }
}
