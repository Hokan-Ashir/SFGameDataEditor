package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.weapons.pieces.list.WeaponPiecesView;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;

public class WeaponsTypesListView extends AbstractModulesView {

    public WeaponsTypesListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "weaponTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillSubViewsMappings() {
        List<Pair<String, Class<? extends PresentableView>>> mappings = new ArrayList<>();
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.1h.weapon"), WeaponPiecesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.2h.weapon"), WeaponPiecesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.bow"), WeaponPiecesView.class));

        addMappings(mappings);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return WeaponsTypesPresenter.class;
    }
}
