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
        mappings.add(createMappingPair("type.default"));
        mappings.add(createMappingPair("type.hand"));
        mappings.add(createMappingPair("type.1h.dagger"));
        mappings.add(createMappingPair("type.1h.sword"));
        mappings.add(createMappingPair("type.1h.axe"));
        mappings.add(createMappingPair("type.1h.mace.spiky"));
        mappings.add(createMappingPair("type.1h.hammer"));
        mappings.add(createMappingPair("type.1h.staff"));
        mappings.add(createMappingPair("type.2h.sword"));
        mappings.add(createMappingPair("type.2h.axe"));
        mappings.add(createMappingPair("type.2h.mace"));
        mappings.add(createMappingPair("type.2h.hammer"));
        mappings.add(createMappingPair("type.2h.staff"));
        mappings.add(createMappingPair("type.2h.spear"));
        mappings.add(createMappingPair("type.2h.halberd"));
        mappings.add(createMappingPair("type.2h.bow"));
        mappings.add(createMappingPair("type.2h.crossbow"));
        mappings.add(createMappingPair("type.1h.mace.blunt"));
        mappings.add(createMappingPair("type.claw"));
        mappings.add(createMappingPair("type.mouth"));
        mappings.add(createMappingPair("type.stone.thrower"));

        addMappings(mappings);
    }
    
    private Pair<String, Class<? extends PresentableView>> createMappingPair(String weaponTypeKey) {
        return new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.WEAPON_GUI, weaponTypeKey), WeaponPiecesView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return WeaponsTypesPresenter.class;
    }
}
