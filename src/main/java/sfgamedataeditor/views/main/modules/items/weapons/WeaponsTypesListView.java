package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class WeaponsTypesListView extends AbstractModulesView {

    public WeaponsTypesListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "weaponTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.1h.weapon"), WeaponPiecesView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.2h.weapon"), WeaponPiecesView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.bow"), WeaponPiecesView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return WeaponsTypesPresenter.class;
    }
}
